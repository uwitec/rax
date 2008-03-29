package test.dao;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import rax.dao.PictureDao;
import rax.model.Picture;

public class PictureDaoTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    private PictureDao dao;

    public void testCount() throws Exception {
        assertTrue(dao.count() >= 0);
    }

    public void testAll() throws Exception {
        int id;
        int cnt;
        int num = 5;
        Picture obj = new Picture();
        cnt = dao.count();
        for (int i = 1; i <= num; i++) {
            obj.setArticleId(i % 2 + 1);
            obj.setTitle("Title" + i);
            obj.setSummary("Summary" + i);
            obj.setMimeType("image/jpeg");
            obj.setSize(1024);
            obj
                    .setFilePath("pictures/2008/03/23/abcdefghijklmnopqrstuvwxyz1234567890.jpeg");
            obj.setUploadDate(new Date());
            id = dao.create(obj);
            assertTrue(id > 0);

            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getTitle().equals("Title" + i));

            obj.setArticleId(-obj.getArticleId());
            assertTrue(dao.update(obj) > 0);

            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getArticleId() < 0);
        }
        assertTrue(dao.count() == cnt + num);

        List<Picture> list = dao.listByArticleId(-1);
        assertTrue(list.size() == Math.round(num / 2));
        assertTrue(dao.delete(obj) > 0);
    }

    public void setDao(PictureDao dao) {
        this.dao = dao;
    }

    protected String[] getConfigLocations() {
        String[] config = new String[] { "applicationContext-dao.xml",
                "applicationContext-test.xml" };
        return config;
    }

}
