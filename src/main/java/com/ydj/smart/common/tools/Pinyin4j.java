package com.ydj.smart.common.tools;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin4j {
	
	public static String getPinyin2(String src) {
		return getPinyin(src).toString().replace("[", "").replace("]", "");
	}
	
	public static Set<String> getPinyin(String src) {
		if (src != null && !src.trim().equalsIgnoreCase("")) {
			char[] srcChar;
			srcChar = src.toCharArray();
			// 汉语拼音格式输出类
			HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
			// 输出设置，大小写，音标方式等
			hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 小写
			hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 无音调
			hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V); // '¨¹'
																			// is
																			// "v"
			String[][] temp = new String[src.length()][];
			for (int i = 0; i < srcChar.length; i++) {
				char c = srcChar[i];
				// 是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
				if (String.valueOf(c).matches("[\u4E00-\u9FA5]+")) { // 中文字符
					try {
						temp[i] = PinyinHelper.toHanyuPinyinStringArray(
								srcChar[i], hanYuPinOutputFormat);
					} catch (BadHanyuPinyinOutputFormatCombination e) {
						e.printStackTrace();
					}
				} else if (((int) c >= 65 && (int) c <= 90)
						|| ((int) c >= 97 && (int) c <= 122)) { // 英文字母
					temp[i] = new String[] { String.valueOf(srcChar[i]) };
				} else { // 其他字符
					temp[i] = new String[] { "" };
				}
			}
			String[] pinyinArray = ExChange(temp);
			Set<String> pinyinSet = new HashSet<String>();
			for (int i = 0; i < pinyinArray.length; i++) {
				pinyinSet.add(pinyinArray[i]);
			}
			return pinyinSet;
		}
		return null;
	}

	/**
	 * 字符串集合转换字符串（逗号分隔）
	 * 
	 * @param stringSet
	 * @return
	 */
	public static String makeStringByStringSet(Set<String> stringSet,
			String separator) {
		StringBuilder str = new StringBuilder();
		int i = 0;
		for (String s : stringSet) {
			if (i == stringSet.size() - 1) {
				str.append(s);
			} else {
				str.append(s + separator);
			}
			i++;
		}
		return str.toString().toLowerCase();
	}

	private static String[] ExChange(String[][] strJaggedArray) {
		String[][] temp = DoExchange(strJaggedArray);
		return temp[0];
	}

	private static String[][] DoExchange(String[][] strJaggedArray) {
		int len = strJaggedArray.length;
		if (len >= 2) {
			int len1 = strJaggedArray[0].length;
			int len2 = strJaggedArray[1].length;
			int newlen = len1 * len2;
			String[] temp = new String[newlen];
			int index = 0;
			for (int i = 0; i < len1; i++) {
				for (int j = 0; j < len2; j++) {
					temp[index] = strJaggedArray[0][i] + strJaggedArray[1][j];
					index++;
				}
			}
			String[][] newArray = new String[len - 1][];
			for (int i = 2; i < len; i++) {
				newArray[i - 1] = strJaggedArray[i];
			}
			newArray[0] = temp;
			return DoExchange(newArray);
		} else {
			return strJaggedArray;
		}
	}

	/**
	 * 只转换汉字为拼音，其他字符不变
	 * 
	 * @param src
	 * @return
	 */
	public static String getPinyinWithMark(String src) {
		if (src != null && !src.trim().equalsIgnoreCase("")) {
			char[] srcChar;
			srcChar = src.toCharArray();
			// 汉语拼音格式输出类
			HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
			// 输出设置，大小写，音标方式等
			hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 小写
			hanYuPinOutputFormat
					.setToneType(HanyuPinyinToneType.WITH_TONE_MARK); // 无音调
			hanYuPinOutputFormat
					.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE); // '¨¹'
																		// is
																		// "v"
			StringBuffer output = new StringBuffer();
			// String[][] temp = new String[src.length()][];
			for (int i = 0; i < srcChar.length; i++) {
				char c = srcChar[i];
				// 是中文转换拼音(我的需求，是保留中文)
				if (String.valueOf(c).matches("[\u4E00-\u9FA5]+")) { // 中文字符
					try {
						String[] temp = PinyinHelper.toHanyuPinyinStringArray(
								srcChar[i], hanYuPinOutputFormat);
						output.append(temp[0]);
						output.append(" ");
					} catch (BadHanyuPinyinOutputFormatCombination e) {
						e.printStackTrace();
					}
				} else { // 其他字符
					output.append(String.valueOf(srcChar[i]));
				}
			}
			return output.toString();
		}
		return null;
	}

	/**
	 * 只转换汉字为拼音，其他字符不变
	 * 
	 * @param src
	 * @return
	 */
	public static String getPinyinWithMark2(String inputString) {
		// 汉语拼音格式输出类
		HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
		// 输出设置，大小写，音标方式等
		hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 小写
		hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK); // 有音调
		hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE); // '¨¹'
																				// is
																				// "u:"
		char[] input = inputString.trim().toCharArray();
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < input.length; i++) {
			// 是中文转换拼音(我的需求，是保留中文)
			if (Character.toString(input[i]).matches("[\u4E00-\u9FA5]+")) { // 中文字符
				try {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(
							input[i], hanYuPinOutputFormat);
					output.append(temp[0]);
					output.append(" ");
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else { // 其他字符
				output.append(Character.toString(input[i]));
			}
		}
		return output.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "我是中国人! I'm Chinese!";
		System.out.println(makeStringByStringSet(getPinyin(str)," "));
		System.out.println(getPinyinWithMark2(str) + 'ŏ');
		System.out.println(getPinyin2(str));
	}
}