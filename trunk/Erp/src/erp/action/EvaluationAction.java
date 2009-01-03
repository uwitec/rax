package erp.action;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import erp.model.Evaluation;

public class EvaluationAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(EvaluationAction.class);

	private String evaluation;
	private List<Evaluation> evaluationList;
	private Map<String, String> byerRank;

	public String parseEvaluation() throws Exception {
		evaluationList = new ArrayList<Evaluation>();
		try {
			String info = "";
			String[] infos = evaluation.split("\n");
			ArrayList<String> infoArray = new ArrayList<String>();
			for (int i = 0; i < infos.length; i++) {
				if (infos[i].trim().length() > 0) {
					infoArray.add(infos[i].trim());
				}
			}

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy.MM.dd HH:mm:ss");
			String dateStr = "([0-9]{4}.[0-9]{1,2}.[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2})";
			Pattern pattern = Pattern.compile(dateStr);
			Matcher matcher;

			for (int i = 0; i < infoArray.size(); i++) {
				info = infoArray.get(i);
				// logger.info("info[" + i + "]:" + info);

				String evContent = "";
				String evName = "";
				String evExplain = "";
				Date evDate = new Date();
				int startPos = info.lastIndexOf("[详情]");
				if (startPos > -1) {
					startPos += 4;
					for (int j = i; j < infoArray.size(); j++) {
						String subInfo = infoArray.get(j);
						matcher = pattern.matcher(subInfo);
						if (matcher.find()) {
							evDate = formatter.parse(matcher.group(1));
							evContent += subInfo.substring(startPos, subInfo
									.lastIndexOf(matcher.group(1)));
							evContent = evContent.trim();
							startPos = j + 1;
							break;
						} else {
							evContent += subInfo.substring(startPos, subInfo
									.length());
							startPos = 0;
						}
					}

					for (int j = startPos; j < infoArray.size(); j++) {
						String subInfo = infoArray.get(j);
						if (subInfo.lastIndexOf("解释") == 0
								&& j + 1 < infoArray.size()) {
							String[] s = infoArray.get(j + 1).split("  ");
							evExplain = s[0];
						} else if (subInfo.lastIndexOf("买家") == 0) {
							String[] s = subInfo.split(" ");
							evName = s[2];
							break;
						}
					}
					logger.info("evContent:" + evContent);
					logger.info("evDate:" + formatter.format(evDate));
					logger.info("evName:" + evName);
					logger.info("evExplain:" + evExplain);
					Evaluation e = new Evaluation();
					e.setContent(evContent);
					e.setName(evName);
					e.setPubDate(evDate);
					e.setExplain(evExplain);
					evaluationList.add(e);
				}
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		return evaluation == null ? SUCCESS : parseEvaluation();
		// return SUCCESS;
	}

	public Map<String, String> getByerRank() {
		Map<String, String> ret = new HashMap<String, String>(16);
		ret.put("b_blue_1.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_blue_1.gif'>");
		ret.put("b_blue_2.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_blue_2.gif'>");
		ret.put("b_blue_3.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_blue_3.gif'>");
		ret.put("b_blue_4.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_blue_4.gif'>");
		ret.put("b_blue_5.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_blue_5.gif'>");
		ret.put("b_red_1.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_red_1.gif'>");
		ret.put("b_red_2.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_red_2.gif'>");
		ret.put("b_red_3.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_red_3.gif'>");
		ret.put("b_red_4.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_red_4.gif'>");
		ret.put("b_red_5.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_red_5.gif'>");
		ret.put("", "(无)");

		/*
		 * http://pics.taobaocdn.com/newrank/b_cap_2.gif
		 */
		return ret;
	}

	public static void main(String[] args) throws Exception {
		char[] cbuf = new char[1024];
		StringBuffer buf = new StringBuffer();
		InputStreamReader is = new InputStreamReader(new FileInputStream(
				"D:/evaluation.txt"));
		int size;
		while ((size = is.read(cbuf)) != -1) {
			buf.append(cbuf, 0, size);
		}
		EvaluationAction action = new EvaluationAction();
		action.setEvaluation(buf.toString());
		action.parseEvaluation();
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public List<Evaluation> getEvaluationList() {
		return evaluationList;
	}

}
