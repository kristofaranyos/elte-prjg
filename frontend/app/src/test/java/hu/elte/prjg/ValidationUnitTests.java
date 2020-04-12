package hu.elte.prjg;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ValidationUnitTests {
    MainActivity mainActivity;
    @Before
    public void createMainActivity(){
        mainActivity = new MainActivity();
    }

    @Test
    public void testEmailValidator_ReturnsTrue() {
        System.out.println("Run testcase: valid email");
        assertEquals(mainActivity.SetValidationEmail("name@email.com"), true);
    }

    @Test
    public void testEmailValidator_MissingDot_ReturnsFalse() {
        System.out.println("Run testcase: invalid email - missing dot");
        assertEquals(mainActivity.SetValidationEmail("name@email"), false);
    }

    @Test
    public void testEmailValidator_MissingAt_ReturnsFalse() {
        System.out.println("Run testcase: invalid email - missing at");
        assertEquals(mainActivity.SetValidationEmail("nameAtemail.com"), false);
    }

    @Test
    public void testEmailValidator_Empty_ReturnsFalse() {
        System.out.println("Run testcase: invalid email - missing at");
        assertEquals(mainActivity.SetValidationEmail(""), false);
    }

    @Test
    public void testPasswordValidator_ReturnsTrue() {
        System.out.println("Run testcase: valid password");
        assertEquals(mainActivity.SetValidationPassword("password"), true);
    }

    @Test
    public void testPasswordValidator_Empty_ReturnsFalse() {
        System.out.println("Run testcase: invalid password - empty");
        assertEquals(mainActivity.SetValidationPassword(""), false);
    }

    @Test
    public void testPasswordValidator_Short_ReturnsFalse() {
        System.out.println("Run testcase: invalid password - short");
        assertEquals(mainActivity.SetValidationPassword("passw"), false);
    }
}
