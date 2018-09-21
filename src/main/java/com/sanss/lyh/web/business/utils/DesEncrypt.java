package com.sanss.lyh.web.business.utils;


import java.security.Key;  
import java.security.SecureRandom;  
import javax.crypto.Cipher;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.DESKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.util.Base64Utils;

import com.sanss.lyh.web.frame.SystemConfig;
  
/**  
 * des加密解密  
 *   
 * @author  
 *   
 */  


public class DesEncrypt {  
  
    Key key;  
    
	public DesEncrypt(String str) {  
        setKey(str);// 生成密匙  
    }  
  
    public DesEncrypt() {  
        setKey("sanss123");          
    }  
  
    /**  
     * 根据参数生成KEY  
     */  
    public void setKey(String strKey) {  
        try {  
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
            this.key  = keyFactory.generateSecret(new DESKeySpec(strKey.getBytes("UTF8")));  
        } catch (Exception e) {  
            throw new RuntimeException(  
                    "Error initializing SqlMap class. Cause: " + e);  
        }  
    }  
  
      
    /**  
     * 加密String明文输入,String密文输出  
     */  
    public String encrypt(String strMing) {  
        byte[] byteMi = null;  
        byte[] byteMing = null;  
        String strMi = "";  
       
        try {  
            byteMing = strMing.getBytes("UTF8");  
            byteMi = this.getEncCode(byteMing);  
            strMi = Base64Utils.encodeToString(byteMi);  
        } catch (Exception e) {  
            throw new RuntimeException(  
                    "Error initializing SqlMap class. Cause: " + e);  
        } finally {          	
            byteMing = null;  
            byteMi = null;  
        }  
        return strMi;  
    }  
  
    /**  
     * 解密 以String密文输入,String明文输出  
     *   
     * @param strMi  
     * @return  
     */  
    public String decrypt(String strMi) {          
        byte[] byteMing = null;  
        byte[] byteMi = null;  
        String strMing = "";  
        try {  
            byteMi = Base64Utils.decodeFromString(strMi);  
            byteMing = this.getDesCode(byteMi);            
            strMing = new String(byteMing, "UTF8");  
        } catch (Exception e) {  
            throw new RuntimeException(  
                    "Error initializing SqlMap class. Cause: " + e);  
        } finally {              
            byteMing = null;  
            byteMi = null;  
        }  
        return strMing;  
    }  
  
    /**  
     * 加密以byte[]明文输入,byte[]密文输出  
     *   
     * @param byteS  
     * @return  
     */  
    private byte[] getEncCode(byte[] byteS) {  
        byte[] byteFina = null;  
        Cipher cipher;  
        try {  
            cipher = Cipher.getInstance("DES");  
            cipher.init(Cipher.ENCRYPT_MODE, key,SecureRandom.getInstance("SHA1PRNG"));  
            byteFina = cipher.doFinal(byteS);  
        } catch (Exception e) {  
            throw new RuntimeException(  
                    "Error initializing SqlMap class. Cause: " + e);  
        } finally {  
            cipher = null;  
        }  
        return byteFina;  
    }  
  
    /**  
     * 解密以byte[]密文输入,以byte[]明文输出  
     *   
     * @param byteD  
     * @return  
     */  
    private byte[] getDesCode(byte[] byteD) {  
        Cipher cipher;  
        byte[] byteFina = null;  
        try {  
            cipher = Cipher.getInstance("DES");  
            cipher.init(Cipher.DECRYPT_MODE, key,SecureRandom.getInstance("SHA1PRNG"));  
            byteFina = cipher.doFinal(byteD);  
        } catch (Exception e) {  
            throw new RuntimeException(  
                    "Error initializing SqlMap class. Cause: " + e);  
        } finally {  
            cipher = null;  
        }  
        return byteFina;  
    }  
  
      
  
    public static void main(String args[])  {  
        DesEncrypt des = new DesEncrypt();  
  
        String str1 = "1234567";  
        // DES加密  
        String str2 = des.encrypt(str1);  
        DesEncrypt des1 = new DesEncrypt();  
        String deStr = des1.decrypt(str2);  
        System.out.println("密文:" + str2);  
        // DES解密  
        System.out.println("明文:" + deStr);  
          
    }  

} 