package com.silu.web.play;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liaojl
 * @date 2018年5月27日下午4:12:44
 */
@Controller
public class PlayGameController {
	// 暂时 不写入数据库 使用死代码 不推荐
	@RequestMapping("/getListPlaies")
	public String getAllPlaies(Model model) {
		return "gameForPlays";
	}

	@GetMapping("playOne")
	public String showOnePic(String picName, Model model) {
		model.addAttribute("picName", picName);
		return "showOnePic";

	}

	@PostMapping("makeImge")
	public void makePic(String titles, String no1, String no2, String no3, HttpServletResponse response) {
		InputStream is = null;
		BufferedImage bi = null;

		if (titles.equals("mvdemo.gif")) {
			try {
				is = PlayGameController.class.getClassLoader().getResourceAsStream("./static/plaies/mv.gif");
				bi = ImageIO.read(is);
				int srcImgWidth = bi.getWidth(null);// 获取图片的宽
				int srcImgHeight = bi.getHeight(null);// 获取图片的高
				// 加水印
				BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = bufImg.createGraphics();
				g.drawImage(bi, 0, 0, srcImgWidth, srcImgHeight, null);
				g.setColor(Color.black); // 根据图片的背景设置水印颜色
				g.setFont(new Font("幼圆", Font.ITALIC, 22)); // 设置字体

				g.rotate(-12 * Math.PI / 180);

				// 设置水印的坐标
				g.drawString(no1.substring(0, no1.length() > 10 ? 10 : no1.length()), 80, 412); // 画出水印
				g.drawString(no2.substring(0, no2.length() > 10 ? 10 : no2.length()), 80, 452); // 画出水印
				g.drawString(no3.substring(0, no3.length() > 10 ? 10 : no3.length()), 80, 494); // 画出水印

				g.dispose();
				// 输出图片

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				ImageIO.write(bufImg, "gif", outputStream);

				String base64Img = "data:image/gif;base64,"
						+ new String(Base64.encodeBase64(outputStream.toByteArray()));
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json");
				String result = "{\"result\":\"SUCCESS\",\"image\":\"" + base64Img + "\"}";
				response.getWriter().write(result);
				// ImageIO.write(bufImg, "jpg", response.getOutputStream());
				// System.out.println("添加水印完成");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} else if (titles.equals("jhdemo.gif")) {
			try {
				is = PlayGameController.class.getClassLoader().getResourceAsStream("./static/plaies/jh.gif");
				bi = ImageIO.read(is);
				int srcImgWidth = bi.getWidth(null);// 获取图片的宽
				int srcImgHeight = bi.getHeight(null);// 获取图片的高
				// 加水印
				BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = bufImg.createGraphics();
				g.drawImage(bi, 0, 0, srcImgWidth, srcImgHeight, null);
				g.setColor(Color.black); // 根据图片的背景设置水印颜色
				g.setFont(new Font("", Font.ITALIC, 15)); // 设置字体


				// 设置水印的坐标
				g.drawString(no1.substring(0, no1.length() > 10 ? 10 : no1.length()), 380, 125); // 画出水印
				g.drawString(no2.substring(0, no2.length() > 10 ? 10 : no2.length()), 465, 125); // 画出水印
				g.drawString(no3.substring(0, no3.length() > 10 ? 10 : no3.length()), 380, 150); // 画出水印
				g.drawString("24", 465, 150); // 画出水印

				g.dispose();
				// 输出图片

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				ImageIO.write(bufImg, "gif", outputStream);

				String base64Img = "data:image/gif;base64,"
						+ new String(Base64.encodeBase64(outputStream.toByteArray()));
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json");
				String result = "{\"result\":\"SUCCESS\",\"image\":\"" + base64Img + "\"}";
				response.getWriter().write(result);
				// ImageIO.write(bufImg, "jpg", response.getOutputStream());
				// System.out.println("添加水印完成");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (titles.equals("zmdemo.gif")) {
			try {
				is = PlayGameController.class.getClassLoader().getResourceAsStream("./static/plaies/zm.gif");
				bi = ImageIO.read(is);
				int srcImgWidth = bi.getWidth(null);// 获取图片的宽
				int srcImgHeight = bi.getHeight(null);// 获取图片的高
				// 加水印
				BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
				Graphics2D g = bufImg.createGraphics();
				g.drawImage(bi, 0, 0, srcImgWidth, srcImgHeight, null);
				g.setColor(Color.BLACK); // 根据图片的背景设置水印颜色
				g.setFont(new Font("楷体", Font.ITALIC, 18)); // 设置字体

				// g.rotate(-12 * Math.PI / 180);

				// 设置水印的坐标
				g.drawString(no1.substring(0, no1.length() > 10 ? 10 : no1.length()), 180, 144); // 画出水印
				g.drawString(no2.substring(0, no2.length() > 50 ? 50 : no2.length()), 45, 180); // 画出水印
				g.drawString(no3.substring(0, no3.length() > 10 ? 10 : no3.length()), 210, 292); // 画出水印

				g.dispose();
				// 输出图片

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				ImageIO.write(bufImg, "gif", outputStream);

				String base64Img = "data:image/gif;base64,"
						+ new String(Base64.encodeBase64(outputStream.toByteArray()));
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json");
				String result = "{\"result\":\"SUCCESS\",\"image\":\"" + base64Img + "\"}";
				response.getWriter().write(result);
				// ImageIO.write(bufImg, "jpg", response.getOutputStream());
				// System.out.println("添加水印完成");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		} else if (titles.equals("jhdemo.gif")) {
			try {
				is = PlayGameController.class.getClassLoader().getResourceAsStream("./static/plaies/" + titles);
				bi = ImageIO.read(is);
				Graphics2D g = bi.createGraphics();
				g.drawImage(bi, 0, 0, 300, 400, null);
				g.setColor(Color.BLACK); // 根据图片的背景设置水印颜色
				g.setFont(Font.getFont("宋体")); // 设置字体

				// 设置水印的坐标
				int x = 200;
				int y = 100;
				g.drawString("", x, y); // 画出水印
				g.dispose();
			} catch (Exception e) {
				// TODO: handle exception
			}

		} else {
			try {
				is = PlayGameController.class.getClassLoader().getResourceAsStream("./static/plaies/");
				bi = ImageIO.read(is);
				Graphics2D g = bi.createGraphics();
				g.drawImage(bi, 0, 0, 300, 400, null);
				g.setColor(Color.BLACK); // 根据图片的背景设置水印颜色
				g.setFont(Font.getFont("宋体")); // 设置字体

				// 设置水印的坐标
				int x = 200;
				int y = 100;
				g.drawString("", x, y); // 画出水印
				g.dispose();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}
}
