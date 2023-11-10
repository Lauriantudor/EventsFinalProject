package ro.sda.eventsFinalProject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class EventsFinalProjectApplicationTests {
Calculator underTest = new Calculator();
	@Test
	void contextLoads() {
		//given
		int nr1 = 20;
		int nr2 =30;

		//when
		int result = underTest.add(nr1,nr2);

		//then
		assertThat(result).isEqualTo(50);
	}
	class Calculator{
		int add(int a, int b){
			return a+b;
		}
	}
}
