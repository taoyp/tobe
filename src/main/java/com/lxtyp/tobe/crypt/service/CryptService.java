package com.lxtyp.tobe.crypt.service;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class CryptService {
    private static String ALGORITHM = "AES";
    private static final String BASE64_KEY = "NG5JAH6TrlHDV7bRl3pfBzSLib7qnkziqV1cX2+kXqo=";
    private static final String BASE64_IV = "HISa6BbhA6a/KOwOup8pRA==";
    private static final int BUFFER_SIZE = 8192;

    public String forEnCrypt(String base) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(BASE64_KEY);
        byte[] ivBytes = Base64.getDecoder().decode(BASE64_IV);

        // 创建SecretKeySpec
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // 创建IvParameterSpec
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // 创建Cipher实例
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // 初始化Cipher为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        // 执行加密
        byte[] encryptedBytes = cipher.doFinal(base.getBytes(StandardCharsets.UTF_8));

        // 将加密后的字节转换为Base64编码的字符串
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String forDeCrypt(String base) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(BASE64_KEY);
        byte[] ivBytes = Base64.getDecoder().decode(BASE64_IV);
        // 将Base64编码的密文字符串解码为字节数组
        byte[] encryptedBytes = Base64.getDecoder().decode(base);

        // 创建SecretKeySpec
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // 创建IvParameterSpec
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // 创建Cipher实例
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // 初始化Cipher为解密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        // 执行解密
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // 将解密后的字节转换为字符串
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // 生成随机密钥
    public String generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256, new SecureRandom());

        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded(); // 获取密钥的字节表示
        String base64Key = Base64.getEncoder().encodeToString(keyBytes); // 将密钥字节转换为Base64字符串

        // 输出生成的AES-256密钥
        System.out.println("AES-256密钥（Base64编码）：" + base64Key);
        return base64Key;
    }

    public String generateIv() {
        byte[] iv = new byte[16];
        // 使用安全的随机数生成器生成IV
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return Base64.getEncoder().encodeToString(iv);
    }

    public void doEncrypt() {
        Path inputFilePath = Paths.get("/Users/lxtyp/Coder/ende/2.pdf");
        Path outputFilePath = Paths.get("/Users/lxtyp/Coder/ende/2En.pdf");
        encryptFile(inputFilePath, outputFilePath);
    }

    public void doDecrypt() {
        long begin = System.currentTimeMillis();
        System.out.println();
        Path inputFilePath = Paths.get("/Users/lxtyp/Coder/ende/2En.pdf");
        Path outputFilePath = Paths.get("/Users/lxtyp/Coder/ende/2De.pdf");
        decryptFile(inputFilePath, outputFilePath);
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }


    // 加密文件
    public void encryptFile(Path inputFilePath, Path outputFilePath) {
        byte[] keyBytes = Base64.getDecoder().decode(BASE64_KEY);
        byte[] ivBytes = Base64.getDecoder().decode(BASE64_IV);

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            try (OutputStream fos = Files.newOutputStream(outputFilePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                 CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

                // 创建一个缓冲区来读取文件
                byte[] buffer = new byte[BUFFER_SIZE];
                int numRead;

                try (InputStream fis = Files.newInputStream(inputFilePath)) {
                    // 读取文件内容并写入CipherOutputStream进行加密
                    while ((numRead = fis.read(buffer)) != -1) {
                        cos.write(buffer, 0, numRead);
                    }
                }

                // CipherOutputStream的flush和close会调用其内部的OutputStream的flush和close
                // 但由于Cipher没有close方法，这里主要是确保CipherOutputStream的flush被调用
                cos.flush();

                // 注意：CipherOutputStream的close会关闭它包装的OutputStream，但不会关闭Cipher
                // Cipher本身不需要关闭，因为它不持有需要释放的资源

            } catch (IOException e) {
                e.printStackTrace();
                // 可以在这里添加错误处理逻辑
            }

        } catch (Exception e) {
            // 可以捕获更通用的异常，但通常不建议这样做，因为它会隐藏具体的错误类型
            e.printStackTrace();
        }
    }

    public void decryptFile(Path encryptedFilePath, Path decryptedFilePath) {
        byte[] keyBytes = Base64.getDecoder().decode(BASE64_KEY);
        byte[] ivBytes = Base64.getDecoder().decode(BASE64_IV);

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            try (InputStream fis = Files.newInputStream(encryptedFilePath);
                 CipherInputStream cis = new CipherInputStream(fis, cipher);
                 OutputStream fos = Files.newOutputStream(decryptedFilePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

                byte[] buffer = new byte[BUFFER_SIZE];
                int numRead;
                // 读取加密文件内容并写入文件（解密过程在CipherInputStream中自动完成）
                while ((numRead = cis.read(buffer)) != -1) {
                    fos.write(buffer, 0, numRead);
                }

            } catch (IOException e) {
                e.printStackTrace();
                // 可以在这里添加错误处理逻辑
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 捕获更通用的异常通常不是最佳实践，因为它会隐藏具体的错误类型
        }
    }
}
