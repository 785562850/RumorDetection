package com.yyjc.utils;

import java.io.File;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.yyjc.domain.po.AccessToken;

public class WeiboAPI {
	@Autowired
	private static JsonObjectUtil jsonObjectUtil;
	private final static String APP_KEY = "2965702373";
	private final static String APP_SECRET = "a00827c7a0f28e68b1b0ff6431895d00";
	private final static String AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize?client_id=YOUR_CLIENT_ID&response_type=code&redirect_uri=YOUR_REGISTERED_REDIRECT_URI";
	private final static String ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token?client_id=YOUR_CLIENT_ID&client_secret=YOUR_CLIENT_SECRET&grant_type=authorization_code&redirect_uri=YOUR_REGISTERED_REDIRECT_URI&code=CODE";
	private final static String REGISTERED_REDIRECT_URI = "http://4137b35a.ngrok.io/RumorDetection/";
	private final static String TOPICS_URL = "https://api.weibo.com/2/search/topics.json";
	private final static String USER_WEIBO_URL="https://api.weibo.com/2/statuses/user_timeline.json";
	private static String CODE = "74773e896c3345897e54d12522692fb9";

	public static void authorize() {
		String url = AUTHORIZE_URL.replace("YOUR_CLIENT_ID", APP_KEY).replace("YOUR_REGISTERED_REDIRECT_URI",
				REGISTERED_REDIRECT_URI);
		
		
		System.out.println(url);
		JSONObject jsonObject = JsonObjectUtil.doGetStr(url);
		CODE = jsonObject.getString("code");
		System.out.println(CODE);
	}

	public static AccessToken getAccessToken() {
		
		AccessToken accessToken = new AccessToken();
		if (CODE != null && !("".equals(CODE))) {
			String url = ACCESS_TOKEN_URL.replace("YOUR_CLIENT_ID", APP_KEY).replace("YOUR_CLIENT_SECRET", APP_SECRET)
					.replace("YOUR_REGISTERED_REDIRECT_URI", REGISTERED_REDIRECT_URI).replace("CODE", CODE);
			JSONObject jsonObject = jsonObjectUtil.doGetStr(url);
			if (jsonObject != null) {
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpireIn(Long.parseLong(jsonObject.getString("expires_in")));
				accessToken.setUid(jsonObject.getString("uid"));
			}
		}
		return accessToken;
	}

	public static String getSinaCookie(String username, String password) throws Exception {
		/*
		 * long waitLoadBaseTime = 10000; int waitLoadRandomTime = 3000; Random
		 * random = new Random(System.currentTimeMillis()); System.setProperty(
		 * "webdriver.chrome.driver",
		 * "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		 * ChromeDriverService service = new ChromeDriverService.Builder()
		 * .usingDriverExecutable( new File(
		 * "F:\\java包\\selenium-server-standalone-2.42.2\\driver\\chromedriver_x64.exe"
		 * )) .usingAnyFreePort().build(); service.start(); // 创建一个 Chrome
		 * 的浏览器实例 WebDriver driver = new RemoteWebDriver(service.getUrl(),
		 * DesiredCapabilities.chrome());
		 * Thread.sleep(waitLoadBaseTime+random.nextInt(waitLoadRandomTime));
		 */

		

		/*
		 * //跳转到baidu首页 driver.get("http://login.weibo.cn/login/");
		 * 
		 * 
		 * //对象识别 By loginName = By.cssSelector("input[name=loginName]"); By
		 * pwdTd = By.cssSelector("input[name=loginPassword]"); By loginBtn =
		 * By.cssSelector("input[name=submit]");
		 * 
		 * WebElement nameEle = driver.findElement(loginName); WebElement pwdEle
		 * = driver.findElement(pwdTd); WebElement btnEle =
		 * driver.findElement(loginBtn); //对象操作 nameEle.sendKeys(username);
		 * pwdEle.sendKeys(password); btnEle.click(); String result =
		 * concatCookie(driver); driver.close(); if
		 * (result.contains("gsid_CTandWM")) { return result; } else { throw new
		 * Exception("weibo login failed"); }
		 */
		return "";
	}

	public static String concatCookie(WebDriver driver) {
		Set<Cookie> cookieSet = driver.manage().getCookies();
		StringBuilder sb = new StringBuilder();
		for (Cookie cookie : cookieSet) {
			sb.append(cookie.getName() + "=" + cookie.getValue() + ";");
		}
		String result = sb.toString();
		return result;
	}
	public static void getUserTimeLine(){
		
		JsonObjectUtil.doGetStr(USER_WEIBO_URL);
		
	}
}
