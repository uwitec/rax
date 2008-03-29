package test.dao;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import rax.dao.AttachmentDao;
import rax.model.Attachment;

public class AttachmentDaoTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    private AttachmentDao dao;

    public void testCount() throws Exception {
        assertTrue(dao.count() >= 0);
    }

    public void testAll() throws Exception {
        int id;
        int cnt;
        int num = 5;
        Attachment obj = new Attachment();
        cnt = dao.count();
        for (int i = 1; i <= num; i++) {
            obj.setArticleId(i % 2 + 1);
            obj.setTitle("Title" + i);
            obj.setUploadName("UploadName" + i);
            obj.setUploadDate(new Date());
            obj.setFilePath("uploads/2008/03/23/upload.file");
            obj.setSize(1024);
            obj.setDownloads(0);
            id = dao.create(obj);
            assertTrue(id > 0);

            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getTitle().equals("Title" + i));

            /*
             * System.out.println(obj.getArticleId());
             * System.out.println(obj.getTitle());
             * System.out.println(obj.getUploadName());
             * System.out.println(obj.getUploadDate());
             * System.out.println(obj.getFilePath());
             * System.out.println(obj.getSize());
             * System.out.println(obj.getDownloads());
             */

            obj.setArticleId(-obj.getArticleId());
            assertTrue(dao.update(obj) > 0);

            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getArticleId() < 0);
        }
        assertTrue(dao.count() == cnt + num);

        List<Attachment> list = dao.listByArticleId(-1);
        assertTrue(list.size() == Math.round(num / 2));
        assertTrue(dao.delete(obj) > 0);
    }

    public void setDao(AttachmentDao dao) {
        this.dao = dao;
    }

    protected String[] getConfigLocations() {
        String[] config = new String[] { "applicationContext-dao.xml",
                "applicationContext-test.xml" };
        return config;
    }

}
