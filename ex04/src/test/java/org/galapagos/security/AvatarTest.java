package org.galapagos.security;

import java.io.File;
import java.io.IOException;

import org.galapagos.config.RootConfig;
import org.galapagos.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		RootConfig.class,
		SecurityConfig.class
})
@Log4j
public class AvatarTest {
	public static final String AVATAR_UPLOAD_DIR = "c:/upload/avatar";
	
	@Test
	public void testUpload() throws IOException {
		String username = "unknown";
		
		File src = new File("c:/Temp/avatar/unknown.png");
		File dest = new File(AVATAR_UPLOAD_DIR, username + ".png"); // 최종적으로 저장하는 곳
		
		Thumbnails.of(src)
			.size(50, 50)
			.toFile(dest);
	
	}

}
