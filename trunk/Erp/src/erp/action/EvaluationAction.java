package erp.action;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
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

	public EvaluationAction() {
		rank = new HashMap<String, String>(16);
		rank.put("b_blue_1.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_blue_1.gif'>");
		rank.put("b_blue_2.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_blue_2.gif'>");
		rank.put("b_blue_3.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_blue_3.gif'>");
		rank.put("b_blue_4.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_blue_4.gif'>");
		rank.put("b_blue_5.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_blue_5.gif'>");
		rank.put("b_red_1.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_red_1.gif'>");
		rank.put("b_red_2.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_red_2.gif'>");
		rank.put("b_red_3.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_red_3.gif'>");
		rank.put("b_red_4.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_red_4.gif'>");
		rank.put("b_red_5.gif",
				"<img src='http://pics.taobaocdn.com/newrank/b_red_5.gif'>");
		rank.put("", "(无)");
		/*
		 * http://pics.taobaocdn.com/newrank/b_cap_2.gif
		 */
	}

	public String parseEvaluation() throws Exception {
		evaList = new ArrayList<Evaluation>();
		try {
			String info = "";
			String[] infos = evaluation.split("\n");
			ArrayList<String> infoArray = new ArrayList<String>();
			for (int i = 0; i < infos.length; i++) {
				info = infos[i].trim();
				if (info.isEmpty() == false) {
					infoArray.add(info);
				}
			}

			Pattern datePattern = Pattern.compile("(\\[[0-9]{4}.[0-9]{1,2}.[0-9]{1,2} [0-9]{2}:[0-9]{2}:[0-9]{2}\\])");
			Pattern levelPattern = Pattern.compile("\\[([\\d]*)－[\\S]*\\]");
			Matcher matcher;

			for (int i = 0; i < infoArray.size(); i++) {
				info = infoArray.get(i);
				//logger.debug("info[" + i + "]:" + info);

				String evDate = "";
				String evContent = "";
				String evName = "";
				String evExplain = "";
				String evRank = "";
				boolean evHaveRank = false;
				
				matcher = datePattern.matcher(info);
				if (matcher.find()) {
					evDate = matcher.group(1);
					evContent = infoArray.get(i - 1);
					if (evContent.startsWith("[解释]")) {
						evExplain = evContent.substring(5);
						evContent = infoArray.get(i - 2);
					}
					
					int posIndex = info.indexOf("买家");
					if (posIndex > -1) {
						String[] subInfo = info.substring(posIndex).split(" ");
						evName = subInfo[2];
					}
					
					matcher = levelPattern.matcher(info);
					if (matcher.find()) {
						try {
							evRank = levelToPicture(Integer.parseInt(matcher.group(1)));
							evHaveRank = true;
						} catch (NumberFormatException e) {}
					}
					
					logger.debug("evContent:" + evContent);
					logger.debug("evDate:" + evDate);
					logger.debug("evName:" + evName);
					logger.debug("evExplain:" + evExplain);
					logger.debug("evHaveRank:" + evHaveRank);
					logger.debug("evRank:" + evRank);
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
		return rank;
	}

	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);
		
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

	private String levelToPicture(int score) {
		String strImage = "";
		switch (score) {
		case 4:
			strImage = "b_red_1.gif";
			break;
		case 11:
			strImage = "b_red_2.gif";
			break;
		case 41:
			strImage = "b_red_3.gif";
			break;
		case 91:
			strImage = "b_red_4.gif";
			break;
		case 151:
			strImage = "b_red_5.gif";
			break;
		case 251:
			strImage = "b_blue_1.gif";
			break;
		case 501:
			strImage = "b_blue_2.gif";
			break;
		case 1001:
			strImage = "b_blue_3.gif";
			break;
		case 2001:
			strImage = "b_blue_4.gif";
			break;
		case 5001:
			strImage = "b_blue_5.gif";
			break;
		case 10001:
			strImage = "b_cap_1.gif";
			break;
		}
		return strImage;
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
