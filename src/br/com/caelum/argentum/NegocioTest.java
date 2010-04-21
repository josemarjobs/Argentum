package br.com.caelum.argentum;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Test;


public class NegocioTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void testDataNula(){
		@SuppressWarnings("unused")
		Negocio n=new Negocio(10,5,null);
	}
	
	@Test
	public void testDataDoNegocioEhImutavel(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negocio n= new Negocio(10,5,c);
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}
}
