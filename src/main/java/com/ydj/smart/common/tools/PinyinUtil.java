package com.ydj.smart.common.tools;

/**
 * @author : wujun
 * @createTime : 2015年11月9日 下午5:10:16
 * @version : 1.0
 * @description :
 */
public class PinyinUtil {

	// 字母Z使用了两个标签，这里有27个值
	// i, u, v都不做声母, 跟随前面的字母
	private  char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈',
			'击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌',
			'挖', '昔', '压', '匝', '座' };
	private  char[] alphatableb = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };
	private  char[] alphatables = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z' };
	private  int[] table = new int[27]; // 初始化
	{
		for (int i = 0; i < 27; ++i) {
			table[i] = gbValue(chartable[i]);
		}
	}

	// 取出汉字的编码
	private  int gbValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GBK");
			if (bytes.length < 2)
				return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}
	
	private  boolean match(int i, int gb) {
		if (gb < table[i])
			return false;
		int j = i + 1;

		// 字母Z使用了两个标签
		while (j < 26 && (table[j] == table[i]))
			++j;
		if (j == 26)
			return gb <= table[j];
		else
			return gb < table[j];
	}
	
	// 主函数,输入字符,得到他的声母,
	// 英文字母返回对应的大小写字母
	// 其他非简体汉字返回 '0' 按参数
	public  char Char2Alpha(char ch, String type) {
		if (ch >= 'a' && ch <= 'z')
			return (char) (ch - 'a' + 'A');// 为了按字母排序先返回大写字母
		// return ch;
		if (ch >= 'A' && ch <= 'Z')
			return ch;

		int gb = gbValue(ch);
		if (gb < table[0])
			return '0';

		int i;
		for (i = 0; i < 26; ++i) {
			if (match(i, gb))
				break;
		}

		if (i >= 26) {
			return '0';
		} else {
			if ("b".equals(type)) {// 大写
				return alphatableb[i];
			} else {// 小写
				return alphatables[i];
			}
		}
	}
	
	// 根据一个包含汉字的字符串返回第一个汉字拼音首字母的字符串
	public String String2AlphaFirst(String SourceStr, String type) {
		String Result = "";
		try {
			Result += Char2Alpha(SourceStr.charAt(0), type);
		} catch (Exception e) {
			Result = "";
		}
		return Result;
	}
	
	public  String String2AlphaFirst2(String SourceStr, String type) {
		String Result = "";
		try {
			for(int i=0 ;i<SourceStr.length() ;i++){
				Result += Char2Alpha(SourceStr.charAt(i), type);
			}
		} catch (Exception e) {
			Result = "";
		}
		return Result;
	}
	
	public static void main(String[] args) {
		PinyinUtil obj = new PinyinUtil();
		System.out.println(obj.String2AlphaFirst2("杨岚清", "B"));
	}
}
