package br.com.caelum.argentum.grafico;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.ui.Coluna;

public class testaReflaction {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Negocio n=new Negocio(40.5,100,Calendar.getInstance());
		Class<Negocio> classe=(Class<Negocio>) n.getClass();
		for(Method method:classe.getMethods()){
			if(method.getAnnotation(Coluna.class)!=null){
				System.out.println(method.invoke(n));
			}
		}

	}
}
