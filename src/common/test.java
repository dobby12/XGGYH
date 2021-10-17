package common;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class test {
	public static void main(String[] args) {
	
		Gson gson = new Gson();
		String jsonString = "{'id':'jekalmin','name':'Min','age':26,'address':'Seoul'}";
		System.out.println(gson.fromJson(jsonString, Member.class));

		Member[] array = gson.fromJson(jsonString, Member[].class);
		List<Member> list = Arrays.asList(array);
//
		System.out.println(list);
//		
//		List<Member> list2 = gson.fromJson(jsonString, new TypeToken<List<Member>>(){}.getType());
//		
//		System.out.println(list2);
	}
}
