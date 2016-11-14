package db;

import org.junit.Test;

import java.sql.SQLException;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static utils.UtilsForAll.getFilePathInRoot;

public class AccessToMSAccessTest {
    @Test
    public void getAccessTest() throws Exception {
        Logger logger = Logger.getLogger("test");
        if (!utils.UtilsForAll.setLoggerConsoleHandler(logger)) assertTrue(false);
        logger.info("логгер запущен");

        AccessToMSAccess accessToMSAccess = new AccessToMSAccess(logger, "c:\\Source\\Cfg777Plus\\Configurations\\aaa.mdb");
        assertTrue(accessToMSAccess.getAccess());
        logger.info("доступ  к БД Access получен");
        assertTrue(accessToMSAccess.closeAccess());
        logger.info("доступ к БД Access отключен");
    }
    @Test
    public void getTableTest() throws Exception {
        Logger logger = Logger.getLogger("test");
        if (!utils.UtilsForAll.setLoggerConsoleHandler(logger)) assertTrue(false);
        logger.info("логгер запущен");

        String strFileName = getFilePathInRoot("database.mdb");
        AccessToMSAccess accessToMSAccess = new AccessToMSAccess(logger, strFileName);

        assertTrue(accessToMSAccess.getAccess());
        logger.info("доступ  к БД Access получен");

        SQLQueryFromMSAccess sqlQueryFromMSAccess = null;
        try {
            sqlQueryFromMSAccess = new SQLQueryFromMSAccess(logger, accessToMSAccess.getConnection());
        } catch (SQLException e) {
            logger.info("ошибка SQLQueryFromMSAccess: " + e.getMessage());
            return;
        }
        String strTableName = "TableForTesting";


        assertTrue(accessToMSAccess.closeAccess());
        logger.info("доступ к БД Access отключен");
    }

}