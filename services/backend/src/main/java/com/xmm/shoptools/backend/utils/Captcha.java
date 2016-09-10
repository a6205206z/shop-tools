package com.xmm.shoptools.backend.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 验证码生成器
 * 
 */
public class Captcha {
	// 图片的宽度。
	private int width = 98;
	// 图片的高度。
	private int height = 37;
	// 验证码字符个数
	private int codeCount = 4;
	// 验证码干扰线数
	private int lineCount = 30;
	// 验证码
	private String code = null;
	// 验证码图片Buffer
	private BufferedImage buffImg = null;

	private char[] codeSequence = { '2', '3', '5', '6', '7', '8', '9' };

	// 生成随机数
	private Random random = new Random();

	public Captcha() {
		this.createCode();
	}

	/**
	 * 
	 * @param width
	 *            图片宽
	 * @param height
	 *            图片高
	 */
	public Captcha(int width, int height) {
		this.width = width;
		this.height = height;
		this.createCode();
	}

	/**
	 * 
	 * @param width
	 *            图片宽
	 * @param height
	 *            图片高
	 * @param codeCount
	 *            字符个数
	 * @param lineCount
	 *            干扰线条数
	 */
	public Captcha(int width, int height, int codeCount, int lineCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		this.lineCount = lineCount;
		this.createCode();
	}

	public void createCode() {
		int codeX = 0;
		int fontHeight = 0;
		fontHeight = height - 5;// 字体的高度
		codeX = width / (codeCount + 3);// 每个字符的宽度

		// 图像buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();

		// 将图像填充为白色
		g.setColor(new Color(235, 235, 235));
		g.fillRect(0, 0, width, height);
		
		// 创建字体
		Font font = this.getFont(fontHeight);
		g.setFont(font);

		// 绘制干扰线
		for (int i = 0; i < lineCount; i++) {
			int xs = getRandomNumber(width);
			int ys = getRandomNumber(height);
			int xe = xs + getRandomNumber(width / 8);
			int ye = ys + getRandomNumber(height / 8);
			g.setColor(getRandomColor());
			g.drawLine(xs, ys, xe, ye);
		}

		StringBuffer randomCode = new StringBuffer();
		// 随机产生验证码字符
		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
			// 设置字体颜色
			g.setColor(getRandomColor());
			// 设置字体位置
			g.drawString(strRand, (i + 1) * codeX, getRandomNumber(height / 2) + 25);
			randomCode.append(strRand);
		}
		code = randomCode.toString();
	}

	public Font getFont(int fontHeight) {
		return new Font("Arial", Font.PLAIN, fontHeight);
	}

	/** 获取随机颜色 */
	private Color getRandomColor() {
		int r = getRandomNumber(100);
		int g = getRandomNumber(100);
		int b = getRandomNumber(100);
		return new Color(r, g, b);
	}

	/** 获取随机数 */
	private int getRandomNumber(int number) {
		return random.nextInt(number);
	}

	public void write(String path) throws IOException {
		OutputStream sos = new FileOutputStream(path);
		this.write(sos);
	}

	public void write(OutputStream sos) throws IOException {
		ImageIO.write(buffImg, "png", sos);
		sos.close();
	}

	public BufferedImage getBuffImg() {
		return buffImg;
	}

	public String getCode() {
		return code;
	}

	/** 字体样式类 */
	class ImgFontByte {

	}
}