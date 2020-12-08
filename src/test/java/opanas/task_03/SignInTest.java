package opanas.task_03;

import com.greenCity.pages.WelcomePage;
import org.testng.annotations.Test;

public class SignInTest {
    @Test
    public void verifyValidData(){
        LoginInPage welcomePage = new WelcomePage()
                .openWebApp("https://ita-social-projects.github.io/GreenCityClient/#/welcome")
                .openSignInButton()
                .fillEmailField("xdknxusqvjeovowpfk@awdrt.com");

    }
}
