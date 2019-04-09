package utils;

import application.Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author lzw
 * @Description 服务端返回数据
 * @date 2019-03-28  22:47:45
 **/
public final class ServerReturn {
    public static int getDataInputStream(String uName, String uPwd,String requestType) throws IOException {
        // 向服务器写数据
        DataOutputStream out = new DataOutputStream(Main.ss.getOutputStream());
        // 从服务器读数据
        DataInputStream in = new DataInputStream(Main.ss.getInputStream());
        out.writeUTF(requestType);// 向服务器发起登陆请求
        out.flush();// 清空缓存
        String msg = uName + "+" + uPwd;// 把用户信息发送给服务器
        out.writeUTF(msg);
        out.flush();
        return in.read();
    }

}
