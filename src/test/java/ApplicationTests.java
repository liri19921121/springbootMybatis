import com.alibaba.druid.util.StringUtils;
import com.common.constant.MovieResourceType;
import com.common.utils.ImgUtil;
import com.mapper.DomesticResourceMapper;
import com.mapper.ResourceMovieTitleMapper;
import com.mapper.ResourceThunderMapper;
import com.pojo.ResourceMovieTitle;
import com.pojo.ResourceThunder;
import com.service.impl.TitleUpdateService;
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
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration
public class ApplicationTests {

	@Autowired
	private ResourceMovieTitleMapper resourceMovieTitleMapper;

	@Autowired
	private DomesticResourceMapper domesticResourceMapper;

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

	@Autowired
	private TitleUpdateService titleUpdateService;

	@Test
	@Transactional
	public void insertTitle() {
			List<ResourceMovieTitle> list = resourceMovieTitleMapper.selectAll();
			int i= 0;
			int j = 0;
			for (ResourceMovieTitle t : list){
				titleUpdateService.updateResourceMovieTitle(t);
			}
	}

	@Autowired
	private ResourceThunderMapper resourceThunderMapper;

	@Test
	@Transactional
	public void insertThunder() {
		List<ResourceThunder> list = resourceThunderMapper.selectAll();
		int i= 0;
		for (ResourceThunder t : list){
			i=i+1;System.out.println("已经处理"+i+"条");
			titleUpdateService.updateResourceThunder(t);
		}
	}

}
