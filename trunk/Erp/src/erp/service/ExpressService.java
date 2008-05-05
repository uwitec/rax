package erp.service;

import erp.model.ExpressTemplate;
import erp.model.ExpressPos;

public class ExpressService {
    
    static public ExpressTemplate getExpress(int expressId) {
        
        ExpressTemplate obj = new ExpressTemplate();
        
        switch (expressId) {
        default:
        case 0: // 韵达
            obj.setDateFormat("yy   MM   dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(133, 264));
            obj.setSrcPhone(new ExpressPos(113, 156));
            obj.setSrcPostCode(new ExpressPos(283, 156));
            obj.setDstPhone1(new ExpressPos(383, 156));
            obj.setDstPhone2(new ExpressPos(383, 167));
            obj.setDstPostCode(new ExpressPos(539, 156));

            obj.setSrcAddressLB(new ExpressPos(10, 10));
            obj.setSrcAddressRT(new ExpressPos(10, 10));
            obj.setSrcName(new ExpressPos(255, 241));

            obj.setDstAddressLB(new ExpressPos(10, 10));
            obj.setDstAddressRT(new ExpressPos(10, 10));
            obj.setDstName(new ExpressPos(493, 241));
            break;
            
        case 1: // 申通
            obj.setDateFormat("yy MM dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(383, 125));
            obj.setSrcPhone(new ExpressPos(130, 156));
            obj.setSrcPostCode(new ExpressPos(232, 156));
            obj.setDstPhone1(new ExpressPos(357, 156));
            obj.setDstPhone2(new ExpressPos(357, 145));
            obj.setDstPostCode(new ExpressPos(459, 156));

            obj.setSrcAddressLB(new ExpressPos(10, 10));
            obj.setSrcAddressRT(new ExpressPos(10, 10));
            obj.setSrcName(new ExpressPos(241, 179));

            obj.setDstAddressLB(new ExpressPos(10, 10));
            obj.setDstAddressRT(new ExpressPos(10, 10));
            obj.setDstName(new ExpressPos(454, 179));
            break;
            
        case 2: // 圆通
            obj.setDateFormat("yy   MM   dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(215, 65));
            obj.setSrcPhone(new ExpressPos(102, 130));
            obj.setSrcPostCode(null);
            obj.setDstPhone1(new ExpressPos(306, 91));
            obj.setDstPhone2(new ExpressPos(414, 91));
            obj.setDstPostCode(null);

            obj.setSrcAddressLB(new ExpressPos(10, 10));
            obj.setSrcAddressRT(new ExpressPos(10, 10));
            obj.setSrcName(new ExpressPos(108, 264));

            obj.setDstAddressLB(new ExpressPos(10, 10));
            obj.setDstAddressRT(new ExpressPos(10, 10));
            obj.setDstName(new ExpressPos(317, 230));
            break;
            
        case 3: // 中通
            obj.setDateFormat("MM   dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(164, 65));
            obj.setSrcPhone(new ExpressPos(147, 159));
            obj.setSrcPostCode(new ExpressPos(269, 159));
            obj.setDstPhone1(new ExpressPos(403, 159));
            obj.setDstPhone2(new ExpressPos(403, 173));
            obj.setDstPostCode(new ExpressPos(524, 159));

            obj.setSrcAddressLB(new ExpressPos(10, 10));
            obj.setSrcAddressRT(new ExpressPos(10, 10));
            obj.setSrcName(new ExpressPos(150, 258));

            obj.setDstAddressLB(new ExpressPos(10, 10));
            obj.setDstAddressRT(new ExpressPos(10, 10));
            obj.setDstName(new ExpressPos(405, 258));
            break;
            
        case 4: // 天天
            obj.setDateFormat("yyyy-MM-dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(122, 255));
            obj.setSrcPhone(new ExpressPos(116, 218));
            obj.setSrcPostCode(new ExpressPos(258, 153));
            obj.setDstPhone1(new ExpressPos(386, 218));
            obj.setDstPhone2(new ExpressPos(482, 218));
            obj.setDstPostCode(new ExpressPos(527, 153));

            obj.setSrcAddressLB(new ExpressPos(10, 10));
            obj.setSrcAddressRT(new ExpressPos(10, 10));
            obj.setSrcName(new ExpressPos(170, 153));

            obj.setDstAddressLB(new ExpressPos(10, 10));
            obj.setDstAddressRT(new ExpressPos(10, 10));
            obj.setDstName(new ExpressPos(403, 153));
            break;
            
        case 5: // 顺丰
            obj.setDateFormat("MM    dd");
            obj.setSize(new ExpressPos(615, 397));
            obj.setDate(new ExpressPos(371, 57));
            obj.setSrcPhone(new ExpressPos(266, 190));
            obj.setSrcPostCode(null);
            obj.setDstPhone1(new ExpressPos(527, 190));
            obj.setDstPhone2(new ExpressPos(527, 201));
            obj.setDstPostCode(null);

            obj.setSrcAddressLB(new ExpressPos(10, 10));
            obj.setSrcAddressRT(new ExpressPos(10, 10));
            obj.setSrcName(new ExpressPos(306, 281));

            obj.setDstAddressLB(new ExpressPos(10, 10));
            obj.setDstAddressRT(new ExpressPos(10, 10));
            obj.setDstName(new ExpressPos(581, 281));
            break;
        }
        return obj;
    }
}
