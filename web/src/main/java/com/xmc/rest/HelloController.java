package com.xmc.rest;

import com.alibaba.fastjson.JSON;
import com.xmc.dao.GoodsRepository;
import com.xmc.dao.UserRepository;
import com.xmc.domain.Goods;
import com.xmc.utils.HTTPClientUtils;
import com.xmc.utils.SSLClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.index.engine.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpRequest;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

@Api(value = "helloController")
@RestController
public class HelloController {

    @Autowired
    UserRepository userRepository;


    @ApiOperation(value = "hello")
    @GetMapping("/hello")
    public String Hello(){
        return "hello 你好啊";
    }

    @ApiOperation(value = "postHello")
    @PostMapping("/postHello")
    @Secured("ROLE_user")
    public String PostHello(){
        return "post Hello!";
    }

//    @GetMapping("/")
//    public String app(){
//        return "hello";
//    }

    @CrossOrigin
    @ApiOperation(value="home")
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @ApiOperation(value="failure")
    @GetMapping("/failure")
    public String failure(){
        return "failure";
    }

    @GetMapping("/github")
    public String oauthGithub(){
        String url = "https://github.com/login/oauth/authorize?" +
                "client_id="+"2377d10af7f72a512796"
                +"&redirect_uri=https://localhost:443/githubCallback?scope=user&state=1&code=1";

        return "<a href=\""+url+"\">登陆</a>";
    }

    @GetMapping("/githubCallback")
    public String githubCallback(@RequestParam(value = "code",required = false) String code,
                                 @RequestParam(value = "state",required = false) String state,
                                 HttpSession session) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        CloseableHttpClient client = HTTPClientUtils.getHttpClient();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost("https://github.com/login/oauth/access_token");
        HashMap<String,String> map = new HashMap<>();
        map.put("code",code);
        map.put("state",state);
        map.put("redirect_uri","https://localhost:443/githubCallback");
        map.put("client_id","2377d10af7f72a512796");
        map.put("client_secret","ed9322a489c06c61e7ad399df2e8bd58c7262082");
        String body = JSON.toJSONString(map);
        httpPost.setEntity((new StringEntity(body)));


        //指定报文头Content-type、User-Agent
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("charset","utf-8");

        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
        CloseableHttpResponse response = client.execute(httpPost);
        byte[] buffer = new byte[1000];
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            System.out.println("body:" + entity);
            InputStream content = response.getEntity().getContent();

            content.read(buffer);

        }


        EntityUtils.consume(entity);
        //释放链接
        response.close();
        System.out.println(new String(buffer,"utf-8"));
        String access_token = new String(buffer,"utf-8").split("&")[0].split("=")[1];
        HttpGet request = new HttpGet("https://api.github.com/user");
        request.setHeader("Authorization","token " + access_token);
        response = client.execute(request);
        entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            System.out.println("body:" + entity);
            InputStream content = response.getEntity().getContent();
            buffer = new byte[1000];
            content.read(buffer);

        }
        String userInfo = new String(buffer,"utf-8");
        System.out.println(userInfo);


        return userInfo;
    }

    @Autowired
    GoodsRepository goodsRepository;


}
