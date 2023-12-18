import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.lifecycle.ViewModel
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher


/*這是一個簡單的加密，之後記得修改為困難的加密*/
// 密鑰管理類別
class KeyRepository {
    private val keyPair: KeyPair by lazy { generateKeyPair() }

    // 生成密鑰對
    private fun generateKeyPair(): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore")
        keyPairGenerator.initialize(
            KeyGenParameterSpec.Builder("userAccountAlias", KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
                .setDigests(KeyProperties.DIGEST_SHA256)
                .build()
        )
        return keyPairGenerator.generateKeyPair()
    }

    // 使用公鑰加密資料
    fun encryptData(data: String): String {
        //創建Cipher 對象，指定 RSA 算法以及填充方案和哈希算法
        val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
        // 使用公鑰初始化 Cipher 對象，設定為加密模式
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey())
        // 將加密後的資料轉換為 Base64 字串返回
        return android.util.Base64.encodeToString(cipher.doFinal(data.toByteArray()), android.util.Base64.DEFAULT)
    }

    // 使用私鑰解密資料
    fun decryptData(encryptedData: String): String {
        //創建Cipher 對象，指定 RSA 算法以及填充方案和哈希算法
        val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
        // 使用私鑰初始化 Cipher 對象，設定為解密模式
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey())
        // 將 Base64 編碼的加密資料解碼並轉換為字串返回
        return String(cipher.doFinal(android.util.Base64.decode(encryptedData, android.util.Base64.DEFAULT)))
    }

    // 取得公鑰
    private fun getPublicKey(): PublicKey {
        val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
        // 從 KeyStore 中取得存儲的密鑰對，再取得其公鑰
        return (keyStore.getEntry("userAccountAlias", null) as KeyStore.PrivateKeyEntry).certificate.publicKey
    }

    // 取得私鑰
    private fun getPrivateKey(): PrivateKey = keyPair.private
}

// ViewModel 類別
class KeyModel : ViewModel() {
    private val keyRepository = KeyRepository()
    private var encryptedData=""
    // 加密資料+儲存
    fun encryptData(data: String,context: Context){
        encryptedData =  keyRepository.encryptData(data)
        val sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("encryptedData", encryptedData)
        editor.apply()
    }

    // 解密資料
    fun decryptData(): String {
        return keyRepository.decryptData(encryptedData)
    }
}