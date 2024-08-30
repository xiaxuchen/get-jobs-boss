package resumeSubImpl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import resumeSubInterface.ResumeSub;
import utils.PropertiesUtils;

import java.util.Properties;

@Slf4j
// @Component
// @ConfigurationProperties(prefix = "driver")
public class BossResumeSub implements ResumeSub {
    public static void main(String[] args) {
        BossResumeSub boss=new BossResumeSub();
        boss.login();
    }
    private String name;
    private String path;
    private EdgeDriver driver;
    private WebDriverWait wait15s;
    private String loginUrl;
    public BossResumeSub() {
        PropertiesUtils.initProperties("application.yaml");
        name =PropertiesUtils.getProperty("driver.name");
        path = PropertiesUtils.getProperty("driver.path");
        loginUrl=  PropertiesUtils.getProperty("login.url");
        System.setProperty(name, path);
        driver= new EdgeDriver();
        wait15s = new WebDriverWait(driver, 15000);
    }

    @Override
    @SneakyThrows
    public void login() {
        driver.get(loginUrl);
        //等待二维码css元素加载
        wait15s.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class*='btn" +
                "-sign-switch ewm-switch']")));
        //点击二维码登录
        driver.findElement(By.cssSelector("[class*='btn-sign-switch ewm-switch']")).click();
        log.info("等待登陆..");
        wait15s.until(ExpectedConditions.presenceOfElementLocated(By.className("nav-figure")));
    }

    @Override
    public ResumeSub city(String[] cities) {
        return null;
    }

    @Override
    public ResumeSub salary(int min, int max) {
        return null;
    }

    @Override
    public ResumeSub experience(String[] experiences) {
        return null;
    }

    @Override
    public ResumeSub jobType(String[] types) {
        return null;
    }

    @Override
    public ResumeSub page(int pageNum) {
        return null;
    }

    @Override
    public void startResumeSubmission() {

    }
}