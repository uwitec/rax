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
        ret.put(9, "EMS");
        ret.put(99, "其他");
        ret.put(-1, "无");
        return ret;
    }

    public ExpressTemplate getExpress(int expressId) {

        /*
         * 坐标系X向右, Y向上, 测量单位为毫米, 可以用index.html上的输入框换算为pt
         * 如设置ExpressPos 对象为null, 可不打印该项
         * 英文/数字内容使用10号字体
         * 普通汉字内容使用11号字体
         * 收件人姓名使用12号字体
         * 
         * Size             标准快递单尺寸 232 * 127
         * Date             发件日期
         * SrcPhone         发件人电话
         * SrcPostCode      发件人邮编
         * DstPhone1        收件人电话1, 移动电话
         * DstPhone2        收件人电话2, 固定电话, 两个电话上下排开行高取13pt
         * DstPostCode      收件人邮编
         * SrcName          发件人姓名
         * DstName          收件人姓名
         * SrcAddressLB     发件人地址框左下角
         * SrcAddressRT     发件人地址框右上角
         * SrcAddressIndent 发件人地址缩进
         * DstAddressLB     收件人地址框左下角, 备注会换行打印在收件人地址后面
         * DstAddressRT     收件人地址框右上角
         * DstAddressIndent 收件人地址缩进
         */
        ExpressTemplate obj = new ExpressTemplate();

        switch (expressId) {
        default:
        case 0: // 韵达
            obj.setDateFormat("yy    MM    dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(102, 261));          // x:36 y:92
            obj.setSrcPhone(new ExpressPos(85, 159));       // x:30 y:56
            obj.setSrcPostCode(new ExpressPos(241, 159));   // x:85 y:56
            obj.setDstPhone1(new ExpressPos(354, 159));     // x:125 y:56
            obj.setDstPhone2(new ExpressPos(354, 172));     // x:125 y:+13pt
            obj.setDstPostCode(new ExpressPos(496, 159));   // x:175 y:56
            
            obj.setSrcName(new ExpressPos(184, 241));       // x:65 y:85
            obj.setDstName(new ExpressPos(468, 241));       // x:165 y:85
            
            obj.setSrcAddressLB(new ExpressPos(57, 170));   // x:20 y:60
            obj.setSrcAddressRT(new ExpressPos(306, 241));  // x:108 y:85
            obj.setDstAddressLB(new ExpressPos(326, 170));  // x:115 y:60
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
            obj.setDateFormat("yy     MM     dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(68, 51));             // x:24 y:18
            obj.setSrcPhone(new ExpressPos(113, 179));       // x:40 y:63
            obj.setSrcPostCode(new ExpressPos(235, 179));    // x:83 y:63
            obj.setDstPhone1(new ExpressPos(496, 264));      // x:175 y:93
            obj.setDstPhone2(new ExpressPos(496, 246));      // x:175 y:-18pt
            obj.setDstPostCode(new ExpressPos(524, 179));    // x:185 y:63

            obj.setSrcName(new ExpressPos(96, 278));         // x:34 y:98
            obj.setDstName(new ExpressPos(363, 264));        // x:128 y:93

            obj.setSrcAddressLB(new ExpressPos(85, 198));    // x:30 y:70
            obj.setSrcAddressRT(new ExpressPos(303, 275));   // x:107 y:97
            obj.setDstAddressLB(new ExpressPos(317, 179));   // x:112 y:63
            obj.setDstAddressRT(new ExpressPos(581, 215));   // x:205 y:76
            break;

        case 3: // 中通
            obj.setDateFormat("MM    dd");
            obj.setSize(new ExpressPos(658, 360));
            obj.setDate(new ExpressPos(111, 57));           // x:39 y:20
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
            obj.setDate(new ExpressPos(91, 264));           // x:32 y:93
            obj.setSrcPhone(new ExpressPos(85, 187));       // x:30 y:66
            obj.setSrcPostCode(new ExpressPos(227, 207));   // x:80 y:73
            obj.setDstPhone1(new ExpressPos(502, 187));     // x:177 y:66
            obj.setDstPhone2(new ExpressPos(354, 187));     // x:125 y:66
            obj.setDstPostCode(new ExpressPos(502, 207));   // x:177 y:73

            obj.setSrcName(new ExpressPos(142, 150));       // x:50 y:53
            obj.setDstName(new ExpressPos(383, 150));       // x:135 y:53
            
            obj.setSrcAddressLB(new ExpressPos(51, 204));   // x:18 y:72
            obj.setSrcAddressRT(new ExpressPos(312, 258));  // x:110 y:91
            obj.setSrcAddressIndent(5);
            
            obj.setDstAddressLB(new ExpressPos(317, 204));  // x:112 y:72
            obj.setDstAddressRT(new ExpressPos(570, 258));  // x:201 y:91
            obj.setDstAddressIndent(5);
            break;
            
        case 5: // 顺丰
            obj.setDateFormat("MM    dd");
            obj.setSize(new ExpressPos(615, 397));
            obj.setDate(new ExpressPos(462, 113));           // x:163 y:40
            obj.setSrcPhone(new ExpressPos(170, 221));       // x:60 y:78
            obj.setSrcPostCode(null);
            obj.setDstPhone1(new ExpressPos(170, 105));      // x:60 y:37
            obj.setDstPhone2(new ExpressPos(43, 105));       // x:15 y:37
            obj.setDstPostCode(null);

            obj.setSrcName(new ExpressPos(227, 289));        // x:80 y:102
            obj.setDstName(new ExpressPos(227, 198));        // x:80 y:70       
            
            obj.setSrcAddressLB(new ExpressPos(71, 241));    // x:25 y:85
            obj.setSrcAddressRT(new ExpressPos(275, 283));   // x:97 y:100
            obj.setDstAddressLB(new ExpressPos(71, 122));    // x:25 y:43
            obj.setDstAddressRT(new ExpressPos(275, 187));   // x:97 y:66
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
            obj.setDate(new ExpressPos(85, 43));            // x:30 y:15
            obj.setSrcPhone(new ExpressPos(85, 198));       // x:30 y:70
            obj.setSrcPostCode(null);
            obj.setDstPhone1(new ExpressPos(354, 198));     // x:125 y:70
            obj.setDstPhone2(new ExpressPos(510, 198));     // x:180 y:70
            obj.setDstPostCode(null);

            obj.setSrcName(new ExpressPos(119, 283));       // x:42 y:100
            obj.setDstName(new ExpressPos(397, 283));       // x:140 y:100

            obj.setSrcAddressLB(new ExpressPos(91, 213));   // x:32 y:75
            obj.setSrcAddressRT(new ExpressPos(312, 278));  // x:110 y:98
            obj.setDstAddressLB(new ExpressPos(360, 213));  // x:127 y:75
            obj.setDstAddressRT(new ExpressPos(575, 278));  // x:203 y:98
            break;
            
        case 9: // EMS
            obj.setDateFormat("yyyy-MM-dd");
            obj.setSize(new ExpressPos(655, 360));          // x:231 y:127
            obj.setDate(new ExpressPos(213, 269));          // x:75 y:95
            obj.setSrcPhone(new ExpressPos(227, 258));      // x:80 y:91
            obj.setSrcPostCode(new ExpressPos(241, 164));   // x:85 y:58
            obj.setDstPhone1(new ExpressPos(496, 258));     // x:175 y:91
            obj.setDstPhone2(new ExpressPos(496, 245));     // x:175 y:-13pt
            obj.setDstPostCode(new ExpressPos(505, 164));   // x:178 y:58

            obj.setSrcName(new ExpressPos(102, 252));       // x:36 y:89
            obj.setDstName(new ExpressPos(369, 252));       // x:130 y:89
            
            obj.setSrcAddressLB(new ExpressPos(57, 176));   // x:20 y:62
            obj.setSrcAddressRT(new ExpressPos(306, 218));  // x:108 y:77
            obj.setDstAddressLB(new ExpressPos(320, 176));  // x:113 y:62
            obj.setDstAddressRT(new ExpressPos(590, 218));  // x:208 y:77
            break; 
        }
        return obj;
    }
}
