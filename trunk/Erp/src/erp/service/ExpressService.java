package erp.service;

import java.util.HashMap;
import java.util.Map;

import erp.model.ExpressPos;
import erp.model.ExpressTemplate;

public class ExpressService {

    public Map<Integer, String> getExpressSel() {
        Map<Integer, String> ret = new HashMap<Integer, String>(16);
        ret.put(0, "韵达");
        ret.put(1, "申通");
        ret.put(2, "圆通");
        ret.put(3, "中通");
        ret.put(4, "天天");
        ret.put(5, "顺丰");
        ret.put(6, "CCES");
        ret.put(7, "彪记");
        ret.put(8, "宅急送");
        ret.put(9, "其他");
        ret.put(-1, "无");
        return ret;
    }

    public ExpressTemplate getExpress(int expressId) {

        ExpressTemplate obj = new ExpressTemplate();

        switch (expressId) {
        default:
        case 0: // 韵达
            obj.setDateFormat("yy   MM   dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(108, 266));          // x:38 y:94
            obj.setSrcPhone(new ExpressPos(85, 159));       // x:30 y:56
            obj.setSrcPostCode(new ExpressPos(241, 159));   // x:85 y:56
            obj.setDstPhone1(new ExpressPos(354, 159));     // x:125 y:56
            obj.setDstPhone2(new ExpressPos(354, 172));     // x:125 y:+13pt
            obj.setDstPostCode(new ExpressPos(496, 159));   // x:175 y:56
            
            obj.setSrcName(new ExpressPos(241, 244));       // x:85 y:86
            obj.setDstName(new ExpressPos(482, 244));       // x:170 y:86
            
            obj.setSrcAddressLB(new ExpressPos(57, 170));   // x:20 y:60
            obj.setSrcAddressRT(new ExpressPos(306, 241));  // x:108 y:85
            obj.setDstAddressLB(new ExpressPos(320, 170));  // x:113 y:60
            obj.setDstAddressRT(new ExpressPos(570, 241));  // x:201 y:85
            break;

        case 1: // 申通
            obj.setDateFormat("yy MM dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(354, 128));          // x:125 y:45
            obj.setSrcPhone(new ExpressPos(108, 156));      // x:38 y:55
            obj.setSrcPostCode(new ExpressPos(204, 156));   // x:72 y:55
            obj.setDstPhone1(new ExpressPos(332, 156));     // x:117 y:55
            obj.setDstPhone2(new ExpressPos(332, 143));     // x:117 y:-13pt
            obj.setDstPostCode(new ExpressPos(445, 156));   // x:157 y:55

            obj.setSrcName(new ExpressPos(105, 179));       // x:37 y:63
            obj.setDstName(new ExpressPos(332, 179));       // x:117 y:63

            obj.setSrcAddressLB(new ExpressPos(96, 201));   // x:34 y:71
            obj.setSrcAddressRT(new ExpressPos(266, 255));  // x:94 y:90
            obj.setDstAddressLB(new ExpressPos(326, 201));  // x:115 y:71
            obj.setDstAddressRT(new ExpressPos(493, 255));  // x:174 y:90
            break;

        case 2: // 圆通
            obj.setDateFormat("yy   MM   dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(184, 68));           // x:65 y:24
            obj.setSrcPhone(new ExpressPos(71, 136));       // x:25 y:48
            obj.setSrcPostCode(null);
            obj.setDstPhone1(new ExpressPos(275, 94));      // x:97 y:33
            obj.setDstPhone2(new ExpressPos(383, 94));      // x:135 y:33
            obj.setDstPostCode(null);

            obj.setSrcName(new ExpressPos(77, 261));        // x:27 y:92
            obj.setDstName(new ExpressPos(289, 232));       // x:102 y:82

            obj.setSrcAddressLB(new ExpressPos(51, 153));   // x:18 y:54
            obj.setSrcAddressRT(new ExpressPos(255, 221));  // x:90 y:78
            obj.setDstAddressLB(new ExpressPos(255, 111));  // x:90 y:39
            obj.setDstAddressRT(new ExpressPos(459, 187));  // x:162 y:66
            break;

        case 3: // 中通
            obj.setDateFormat("MM   dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(133, 65));           // x:47 y:23
            obj.setSrcPhone(new ExpressPos(119, 159));      // x:42 y:56
            obj.setSrcPostCode(new ExpressPos(241, 159));   // x:85 y:56
            obj.setDstPhone1(new ExpressPos(374, 159));     // x:132 y:56
            obj.setDstPhone2(new ExpressPos(374, 172));     // x:132 y:+13pt
            obj.setDstPostCode(new ExpressPos(496, 159));   // x:175 y:56

            obj.setSrcName(new ExpressPos(119, 255));       // x:42 y:90
            obj.setDstName(new ExpressPos(374, 255));       // x:132 y:90
            
            obj.setSrcAddressLB(new ExpressPos(113, 187));  // x:40 y:66
            obj.setSrcAddressRT(new ExpressPos(309, 252));  // x:109 y:89
            obj.setDstAddressLB(new ExpressPos(366, 187));  // x:129 y:66
            obj.setDstAddressRT(new ExpressPos(567, 252));  // x:200 y:89
            break;

        case 4: // 天天
            obj.setDateFormat("yyyy-MM-dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(94, 255));           // x:33 y:90
            obj.setSrcPhone(new ExpressPos(85, 218));       // x:30 y:77
            obj.setSrcPostCode(new ExpressPos(230, 153));   // x:81 y:54
            obj.setDstPhone1(new ExpressPos(357, 218));     // x:126 y:77
            obj.setDstPhone2(new ExpressPos(442, 218));     // x:156 y:77
            obj.setDstPostCode(new ExpressPos(499, 153));   // x:176 y:54

            obj.setSrcName(new ExpressPos(139, 153));       // x:49 y:54
            obj.setDstName(new ExpressPos(374, 153));       // x:132 y:54
            
            obj.setSrcAddressLB(new ExpressPos(57, 173));   // x:20 y:61
            obj.setSrcAddressRT(new ExpressPos(312, 207));  // x:110 y:73
            obj.setDstAddressLB(new ExpressPos(323, 173));  // x:114 y:61
            obj.setDstAddressRT(new ExpressPos(573, 207));  // x:202 y:73
            break;

        case 5: // 顺丰
            obj.setDateFormat("MM    dd");
            obj.setSize(new ExpressPos(615, 397));
            obj.setDate(new ExpressPos(315, 57));           // x:111 y:20
            obj.setSrcPhone(new ExpressPos(207, 193));      // x:73 y:68
            obj.setSrcPostCode(null);
            obj.setDstPhone1(new ExpressPos(468, 193));     // x:165 y:68
            obj.setDstPhone2(new ExpressPos(468, 206));     // x:165 y:+13pt
            obj.setDstPostCode(null);

            obj.setSrcName(new ExpressPos(252, 281));       // x:89 y:99
            obj.setDstName(new ExpressPos(519, 281));       // x:183 y:99       
            
            obj.setSrcAddressLB(new ExpressPos(43, 215));   // x:15 y:76
            obj.setSrcAddressRT(new ExpressPos(309, 261));  // x:109 y:92
            obj.setDstAddressLB(new ExpressPos(312, 215));  // x:110 y:76
            obj.setDstAddressRT(new ExpressPos(575, 261));  // x:203 y:92
            break;
            
        case 6: // CCES
            //obj.setDateFormat("MM    dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(null);
            obj.setSrcPhone(new ExpressPos(108, 145));      // x:38 y:51
            obj.setSrcPostCode(null);
            obj.setDstPhone1(new ExpressPos(312, 145));     // x:110 y:51
            obj.setDstPhone2(new ExpressPos(312, 158));     // x:110 y:+13pt
            obj.setDstPostCode(null);

            obj.setSrcName(new ExpressPos(116, 176));       // x:41 y:62
            obj.setDstName(new ExpressPos(349, 176));       // x:123 y:62

            obj.setSrcAddressLB(new ExpressPos(99, 193));   // x:35 y:68
            obj.setSrcAddressRT(new ExpressPos(275, 230));  // x:97 y:81
            obj.setDstAddressLB(new ExpressPos(326, 193));  // x:115 y:68
            obj.setDstAddressRT(new ExpressPos(505, 247));  // x:178 y:87
            break;

        case 7: // 彪记
            obj.setDateFormat("dd-MM-yyyy");
            obj.setSize(new ExpressPos(689, 289));          // x:243 y:102
            obj.setDate(new ExpressPos(425, 193));          // x:150 y:68
            obj.setSrcPhone(new ExpressPos(108, 71));       // x:22 y:25
            obj.setSrcPostCode(null);
            obj.setDstPhone1(new ExpressPos(283, 77));      // x:100 y:27
            obj.setDstPhone2(new ExpressPos(283, 90));      // x:100 y:-13pt
            obj.setDstPostCode(null);

            obj.setSrcName(new ExpressPos(145, 57));        // x:51 y:20
            obj.setDstName(new ExpressPos(320, 57));        // x:113 y:20

            obj.setSrcAddressLB(new ExpressPos(43, 85));    // x:15 y:30
            obj.setSrcAddressRT(new ExpressPos(264, 173));  // x:93 y:61
            obj.setDstAddressLB(new ExpressPos(266, 108));  // x:94 y:38
            obj.setDstAddressRT(new ExpressPos(485, 173));  // x:171 y:61
            break;
            
        case 8: // 宅急送
            obj.setDateFormat("MM dd");
            obj.setSize(new ExpressPos(658, 397));          // x:232 y:140
            obj.setDate(new ExpressPos(85, 43));          // x:30 y:15
            obj.setSrcPhone(new ExpressPos(85, 198));       // x:30 y:70
            obj.setSrcPostCode(null);
            obj.setDstPhone1(new ExpressPos(354, 198));      // x:125 y:70
            obj.setDstPhone2(new ExpressPos(510, 198));      // x:180 y:70
            obj.setDstPostCode(null);

            obj.setSrcName(new ExpressPos(119, 283));        // x:42 y:100
            obj.setDstName(new ExpressPos(397, 283));        // x:140 y:100

            obj.setSrcAddressLB(new ExpressPos(91, 213));    // x:32 y:75
            obj.setSrcAddressRT(new ExpressPos(312, 278));  // x:110 y:98
            obj.setDstAddressLB(new ExpressPos(360, 213));  // x:127 y:75
            obj.setDstAddressRT(new ExpressPos(575, 278));  // x:203 y:98
            break;

        }
        return obj;
    }
}
