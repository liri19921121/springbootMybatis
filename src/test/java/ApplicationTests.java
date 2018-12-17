import com.alibaba.druid.util.StringUtils;
import com.common.constant.MovieResourceType;
import com.common.utils.ImgUtil;
import com.github.pagehelper.PageHelper;
import com.mapper.ResourceMovieTitleMapper;
import com.pojo.DomesticResource;
import com.pojo.ResourceMovieTitle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration
public class ApplicationTests {

	@Autowired
	private ResourceMovieTitleMapper resourceMovieTitleMapper;

	@Test
	public void testSelenium() {
		System.getProperties().setProperty("webdriver.chrome.driver", "G:/chromedriver_win32/chromedriver.exe");
		WebDriver webDriver = new ChromeDriver();
		webDriver.get("https://www.886er.com/vod/17/11675-1.html");
		WebElement webElement = webDriver.findElement(By.xpath("/html"));
		System.out.println(webElement.getAttribute("outerHTML"));
		webDriver.close();
	}
	@Test
	public void imgDown() {

		//写入本地
		String result = ImgUtil.uploadQianURL("cc","https://img.997pp.com/tp/2018/08/2gmtdxallsu.png");
		if (StringUtils.isEmpty(result)){
			System.out.println("下载失败");
		}else{
			System.out.println("下载成功"+result);
		}
	}

	@Test
	public void insertTitle() {
		Example example = new Example(ResourceMovieTitle.class);
		example.createCriteria().andEqualTo("indexColumn", MovieResourceType.DOMESTIC);
		List<ResourceMovieTitle> list = resourceMovieTitleMapper.selectByExample(example);
		for (ResourceMovieTitle t : list){


		}

	}

}
