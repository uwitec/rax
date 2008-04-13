package rax.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import rax.model.Faq;
import rax.service.FaqService;

import com.opensymphony.xwork2.ActionSupport;

public class FaqAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(FaqAction.class);

    private FaqService faqService = null;
    private String question;
    private String answer;
    private boolean pub;

    Map pubSel;

    public String input() throws Exception {
        pub = true;
        pubSel = new HashMap();
        pubSel.put("Not pub", false);
        pubSel.put("Pub", true);
        return INPUT;
    }

    public String add() throws Exception {
        return execute();
    }

    @Override
    public String execute() throws Exception {

        Faq faq = new Faq();
        faq.setQuestion(question);
        faq.setAnswer(answer);
        faq.setPubDate(new Date());
        faq.setPub(pub);

        try {
            faqService.createFaq(faq);
        } catch (Exception ex) {
            logger.error(ex.toString());
            return ERROR;
        }

        return SUCCESS;
    }

    public void setFaqService(FaqService service) {
        faqService = service;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isPub() {
        return pub;
    }

    public void setPub(boolean pub) {
        this.pub = pub;
    }

    public Map getPubSel() {
        return pubSel;
    }
}
