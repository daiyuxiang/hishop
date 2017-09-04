package cn.com.do1.component.common.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

/**
 * JSON utility class
 * 
 * @since 2008-04-21
 */
public class JSONUtil {

	private Map map = new LinkedHashMap();

	/**
	 * 添加一个 JSON 属性，值为一个字符串，重复添加时产生数组
	 * <p/>
	 * 
	 * add("name", "value");<br/>
	 * 添加一个字符串，产生的 JSON 如：{"name":"value"}
	 * <p/>
	 * 
	 * add("name", "value1");<br/>
	 * add("name", "value2");<br/>
	 * 添加两个同属性的字符串，产生的 JSON 如：{"name":["value1", "value2"]}
	 * <p/>
	 * 
	 * @param key
	 *            JSON 属性名
	 * @param str
	 *            字符串格式的属性值
	 */
	public void add(String key, String value) {
		addElement(key, value);
	}

	public void add(String key, int num) {
		addElement(key, new Integer(num));
	}

	public void add(String key, boolean b) {
		addElement(key, new Boolean(b));
	}

	/**
	 * 添加一个 JSON 属性，值为一个 JSON，重复添加时产生 JSON 数组
	 * <p/>
	 * 
	 * Json json1 = new Json();<br/>
	 * json1.add("name1", "value1");<br/>
	 * json1.add("name2", "value2");<br/>
	 * Json json = new Json();<br/>
	 * json.add("message", json1);<br/>
	 * 添加一个 JSON，产生的 JSON 如：{"message":{"name1":"value1", "name2":"value2"}}
	 * <p/>
	 * 
	 * Json json1 = new Json();<br/>
	 * json1.add("name1", "value1");<br/>
	 * json1.add("name2", "value2");<br/>
	 * Json json2 = new Json();<br/>
	 * json2.add("name1", "value3");<br/>
	 * json2.add("name2", "value4");<br/>
	 * Json json = new Json();<br/>
	 * json.add("message", json1);<br/>
	 * json.add("message", json2);<br/>
	 * 添加两个同属性的 JSON，产生的 JSON 如：{"message":[{"name1":"value1", "name2":"value2"}, {"name1":"value3", "name2":"value4"}]}
	 * <p/>
	 * 
	 * @param key
	 *            JSON 属性名
	 * @param json
	 *            JSON 格式的属性值
	 */
	public void add(String key, JSONUtil json) {
		addElement(key, json);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		int k = 0;
		for (Iterator i = map.keySet().iterator(); i.hasNext();) {
			String key = (String) (i.next());
			Object obj = map.get(key);
			if (k > 0) {
				sb.append(",");
			}
			appendKey(sb, key);
			if (obj instanceof String) {
				appendString(sb, (String) obj);
			} else if (obj instanceof List) {
				appendList(sb, (List) obj);
			} else if (obj instanceof JSONUtil) {
				appendJson(sb, (JSONUtil) obj);
			} else {
				appendOther(sb, obj);
			}
			k++;
		}
		sb.append("}");
		return sb.toString();
	}

	private void addElement(String key, Object obj) {
		if (!map.containsKey(key)) {
			if (obj instanceof JSONUtil) {
				List list = new ArrayList();
				list.add(obj);
				map.put(key, list);
			} else {
				map.put(key, obj);
			}
			return;
		}
		Object o = map.remove(key);
		if (o instanceof List) {
			((List) o).add(obj);
			map.put(key, o);
			return;
		}
		List list = new ArrayList();
		list.add(o);
		list.add(obj);
		map.put(key, list);
	}

	/**
	 * Append JSON property name
	 * 
	 * @param sb
	 * @param key
	 */
	private void appendKey(StringBuilder sb, String key) {
		sb.append("\"").append(key).append("\":");
	}

	/**
	 * Append JSON property value that is a String
	 * 
	 * @param sb
	 * @param str
	 */
	private void appendString(StringBuilder sb, String str) {
		str = str.replace("\"", "\\\"");
		sb.append("\"").append(str).append("\"");
	}

	/**
	 * Append JSON property value that is a Integer
	 * 
	 * @param sb
	 * @param num
	 */
	private void appendOther(StringBuilder sb, Object obj) {
		sb.append(obj);
	}

	/**
	 * Append JSON property value that is a List
	 * 
	 * @param sb
	 * @param list
	 */
	private void appendList(StringBuilder sb, List list) {
		sb.append("[");
		for (int j = 0, m = list.size(); j < m; j++) {
			if (j > 0) {
				sb.append(",");
			}
			Object obj = list.get(j);
			if (obj instanceof String) {
				appendString(sb, (String) obj);
			} else if (obj instanceof JSONUtil) {
				appendJson(sb, (JSONUtil) obj);
			} else {
				appendOther(sb, obj);
			}
		}
		sb.append("]");
	}

	/**
	 * Append JSON property value that is a JSON
	 * 
	 * @param sb
	 * @param json
	 */
	private void appendJson(StringBuilder sb, JSONUtil json) {
		sb.append(json.toString());
	}
	
	public static JSONUtil mapToJson(Map m){
		JSONUtil json = new JSONUtil();
		if(m.size()>0){
			for(Object key:m.keySet()){
				json.add(key.toString(), m.get(key).toString());
			}
		}
		return json;
	}
}