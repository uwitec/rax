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
	private List<Evaluation> evaList;
	private Map<String, String> rank;

	public String parseEvaluation() throws Exception {
		evaList = new ArrayList<Evaluation>();
		try {
			String info = "";
			String[] infos = evaluation.split("\n");
			ArrayList<String> infoArray = new ArrayList<String>();
			for (int i = 0; i < infos.length; i++) {
				if (infos[i].trim().length() > 0) {
					infoArray.add(infos[i].trim());
				}
			}

			String dateStr = "([0-9]{4}.[0-9]{1,2}.[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2})";
			Pattern pattern = Pattern.compile(dateStr);
			Matcher matcher;

			Pattern lvPat = Pattern.compile("\\[([\\d]*)－[\\S]*\\]");

			for (int i = 0; i < infoArray.size(); i++) {
				info = infoArray.get(i);
				// logger.info("info[" + i + "]:" + info);

				String evContent = "";
				String evName = "";
				String evExplain = "";
				String evRank = "";
				boolean evHaveRank = false;
				String evDate = "";
				int startPos = info.lastIndexOf("[详情]");
				if (startPos > -1) {
					evHaveRank = (startPos == 0);
					startPos += 4;
					for (int j = i; j < infoArray.size(); j++) {
						String subInfo = infoArray.get(j);
						matcher = pattern.matcher(subInfo);
						if (matcher.find()) {
							evDate = matcher.group(1);
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
						if (subInfo.indexOf("解释") == 0
								&& j + 1 < infoArray.size()) {
							String[] s = infoArray.get(j + 1).split("  ");
							evExplain = s[0];
						} else if (subInfo.indexOf("买家") == 0) {
							String[] s = subInfo.split(" ");
							/*
							for (int k = 0; k < s.length; k++) {
								logger.info("[" + s[k] + "]");
							}
							*/
							evName = s[2];

							matcher = lvPat.matcher(subInfo);
							if (matcher.find()) {
								logger.debug(matcher.group(1));
								try {
									switch (Integer.parseInt(matcher.group(1))) {
									case 4:
										evRank = "b_red_1.gif";
										break;
									case 11:
										evRank = "b_red_2.gif";
										break;
									case 41:
										evRank = "b_red_3.gif";
										break;
									case 91:
										evRank = "b_red_4.gif";
										break;
									case 151:
										evRank = "b_red_5.gif";
										break;
									case 251:
										evRank = "b_blue_1.gif";
										break;
									case 501:
										evRank = "b_blue_2.gif";
										break;
									case 1001:
										evRank = "b_blue_3.gif";
										break;
									case 2001:
										evRank = "b_blue_4.gif";
										break;
									case 5001:
										evRank = "b_blue_5.gif";
										break;
									case 10001:
										evRank = "b_cap_1.gif";
										break;
									}
								} catch (NumberFormatException e) {
								}
							}
							break;
						}
					}
					logger.info("evContent:" + evContent);
					logger.info("evDate:" + evDate);
					logger.info("evName:" + evName);
					logger.info("evExplain:" + evExplain);
					logger.info("evHaveRank:" + evHaveRank);
					logger.info("evRank:" + evRank);
					Evaluation e = new Evaluation();
					e.setContent(evContent);
					e.setName(evName);
					e.setPubDate(evDate);
					e.setExplain(evExplain);
					e.setRank(evRank);
					e.setHaveRank(evHaveRank);
					evaList.add(e);
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

	public Map<String, String> getRank() {
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

	public List<Evaluation> getEvaList() {
		return evaList;
	}

}
