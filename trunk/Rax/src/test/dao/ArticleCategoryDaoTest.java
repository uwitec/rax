package test.dao;

import java.util.Date;
import java.util.List;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import rax.dao.ArticleCategoryDao;
import rax.model.ArticleCategory;

public class ArticleCategoryDaoTest extends
        AbstractTransactionalDataSourceSpringContextTests {

    private ArticleCategoryDao dao;

    public void testList() throws Exception {
        List<ArticleCategory> list = dao.listAll();
        assertNotNull(list);
    }

    public void testAll() throws Exception {
        int id = 0;
        int num = 5;
        int root = 0;
        int cnt;
        List<ArticleCategory> list;
        ArticleCategory rootObj;
        ArticleCategory obj = new ArticleCategory();
        for (int i = 1; i <= num; i++) {
            obj.setParentId(id);
            obj.setName("Name" + id);
            obj.setSummary("Summary" + id);
            obj.setCreateDate(new Date());
            id = dao.create(obj);
            assertTrue(id > 0);

            if (root == 0)
                root = id;

            obj = dao.read(id);
            assertNotNull(obj);
            assertTrue(obj.getName().equals("Name" + obj.getParentId()));

            obj.setLthread(-(num * 2 - i + 1));
            obj.setRthread(-i);
            assertTrue(dao.update(obj) > 0);

            obj = dao.read(id);
            assertNotNull(obj);

            assertTrue(obj.getLthread() == -(num * 2 - i + 1));
            assertTrue(obj.getRthread() == -i);
            
            traceObj(obj);
        }

        cnt = dao.countChildCategoryByCategoryId(root);
        assertTrue(cnt == 1);

        cnt = dao.countSubCategoryByCategoryId(root);
        assertTrue(cnt == num - 1);

        list = dao.listChildByCategoryId(root);
        assertTrue(list.size() == 1);
        obj = list.get(0);
        assertTrue(list.get(0).getParentId() == root);

        rootObj = dao.read(root);
        list = dao.listSubCategoryByCategoryId(root);
                
        assertTrue(list.size() == num - 1);
        for (ArticleCategory category : list) {
            assertTrue(category.getLthread() > rootObj.getLthread());
            assertTrue(category.getRthread() < rootObj.getRthread());
            obj = category;
        }

        list = dao.listPathToCategory(obj.getLthread(), obj.getRthread());
        assertTrue(list.size() == num);

        assertTrue(dao.adjustLthread(rootObj.getLthread(), 1) == num);
        assertTrue(dao.adjustRthread(obj.getRthread(), 1) == num);

        list = dao.listPathToCategory(obj.getLthread(), obj.getRthread());
        assertTrue(list.size() == num - 1);

        assertTrue(dao.delete(obj) > 0);
    }

    private void traceObj(ArticleCategory obj) {
        System.out.println(obj.getId());
        System.out.println(obj.getParentId());
        System.out.println(obj.getName());
        System.out.println(obj.getSummary());
        System.out.println(obj.getLthread());
        System.out.println(obj.getRthread());
        System.out.println(obj.getCreateDate());
        System.out.println();
    }
    
    public void setDao(ArticleCategoryDao dao) {
        this.dao = dao;
    }

    protected String[] getConfigLocations() {
        String[] config = new String[] { "applicationContext-dao.xml",
                "applicationContext-test.xml" };
        return config;
    }

}
