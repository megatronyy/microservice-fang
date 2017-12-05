package com.fang.cloud;

import com.fang.cloud.entity.UserAccount;
import com.fang.cloud.entity.UserData;
import com.fang.cloud.mapper.UserAccountMapper;
import com.fang.cloud.mapper.UserDataMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroserviceProviderApplicationTests {
	@Autowired
	private UserDataMapper userDataMapper;

	@Autowired
	private UserAccountMapper userAccountMapper;

	@Test
	public void contextLoads() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userid", 100000);
		UserData userData = userDataMapper.getUserData(param);
		assert userData.getUserid()>0;
		//UserAccount userAccount = userAccountMapper.selectByPrimaryKey(100000);
		//assert userAccount.getUserid()>0;
	}

}
