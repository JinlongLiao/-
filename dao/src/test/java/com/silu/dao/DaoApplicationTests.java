package com.silu.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.junit.Test;

public class DaoApplicationTests {

	@Test
	public void contextLoads() throws UnsupportedEncodingException {
		System.out.println(URLDecoder.decode("	%E5%AF%BC%E8%88%AA%E6%A0%8F", "utf-8"));
	}

}
