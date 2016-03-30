package com.nutstudio.nutenglish.Activity;
import android.app.*;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.PagerAdapter;
import android.widget.*;
import android.os.*;
import android.content.*;
import android.support.v4.view.*;

import java.text.SimpleDateFormat;
import java.util.*;
import android.view.*;
import android.view.View.*;

import com.nutstudio.nutenglish.R;
import com.nutstudio.nutenglish.Tools.UITools;
import com.nutstudio.nutenglish.Tools.WordDB;
import com.nutstudio.nutenglish.service.LogService;

public class Welcome extends Activity
{


	private String dc[]={"engine lathe" , " turret lathe" , "vertical turning mill ", "automatic lathe" , "horizontal" ," vertical" , "milling machine" ," boring mill ","drilling machine ","grinding machine ","machine tool " ,"headstock" ,"chuck ","tool post" ,"carriage" , "tailstock ","spindle" ,"handwheel" ,"gearbox ","guideway " ,"cast iron ","grey cast iron" ,"nodular cast iron"  ,"bar stock" ," workpiece" ,"nut" ,"screw" ,"twist drills ","boring bars" ,"process" ,"instruction ","mainframe","application ","processor ","temporary" , "permanent ", "operator" ," instruction  system"  ,"lump" ," metal" , "magnetic" ,"shield ","workpiece ","hydraulic" ,"tank" ,"filter" , "altitude" , "friction ", "AC " , "DC" ," apparatus" , "blower ","controller" ,"crane" ," excitation ","motor "," rectifier ","resistor" , "capacitor ", "inductor" ,"torque" , "hoist" ,"comparison" ,"density" ,"diesel" ,"range" ,"vehicle ","simplicity" ,"PLC" ,"characteristic" ,"command" ,"portable" ,"robot ","universal" ,"weld" ,"conception ","performance ","interface "  ,"input ","keyboard" ,"stress" , "terminal" ,"CAD" ,"off-line" ,"CAM"};
	private String dcN[]={"普通车床"," 转塔车床", "立式车床", "自动车床", "卧式", "立式", "铣床" ," 镗床" , "钻床", "磨床" ," 机床", " 主轴箱", "卡盘" , "刀架", "溜板箱" , "尾架", "轴"," 手轮"," 齿轮箱", "导轨", "铸铁"," 灰铸铁", "球磨铸铁",  "棒料"," 工件" , "螺母"," 螺杆" , "麻花钻", "镗杆", "加工", "指令", "大型机"," 应用", "处理器", "暂时的" , "永久的", "运算数", "指令系统"," 块状", "金属", "磁的", "屏蔽"," 工件", "液压的", "水箱", "过滤器", "海拔", "摩擦", "交流", "直流"," 仪器", "鼓风机", "控制器", "起重机"," 励磁", "电动机", "整流器", "电阻器", "电容器"," 电感器", "转矩" , "卷扬机" , "比较"," 密度"," 柴油机", "范围"," 车辆", "简易性", "可编程控制器"," 特点", "命令", "便携式的"," 机器人", "通用的", "焊接", "概念", "性能", "接口",  "输入", "键盘", "压力", "终端", "计算机辅助设计", "脱机" , "计算机辅助制造"};
	private String dcText[]={
			"普通车床是能对轴、盘、环等多种类型工件进行多种工序加工的卧式车床，常用于加工工件的内外回转表面、端面和各种内外螺纹，采用相应的刀具和附件，还可进行钻孔、扩孔、攻丝和滚花等。",
			"具有回转轴线与主轴轴线垂直的转塔刀架，并可顺序转位切削工件的车床。",
			"立式车床一般可分为单柱式和双柱式。小型立式车床一般做成单柱式，大型立式车床做成双柱式。立式车床结构的主要特点是它的主轴处于垂直位置。立式车床的主要特点是：工作台在水平面内，工件的安装调整比较方便。",
			"自动车床是一种高性能，高精度，低噪音的走刀式自动车床，是通过凸轮来控制加工程序的自动加工机床。另外也有一些数控自动车床与气动自动车床以及走心式自动车床，其基本核心是可以经过一定设置与调教后可以长时间自动加工同一种产品。",
			"类似床一样，物体长比高的数值大的一种结构",
			"类似柱子一样，物体的高比长的数值的一种结构",
			"铣床（millingmachine）系主要指用铣刀在工件上加工各种表面的机床。通常铣刀旋转运动为主运动，工件（和）铣刀的移动为进给运动。它可以加工平面、沟槽，也可以加工各种曲面、齿轮等。铣床是用铣刀对工件进行铣削加工的机床。铣床除能铣削平面、沟槽、轮齿、螺纹和花键轴外，还能加工比较复杂的型面，效率较刨床高，在机械制造和修理部门得到广泛应用。",
			"主要用镗刀对工件已有的预制孔进行镗削的机床。通常，镗刀旋转为主运动，镗刀或工件的移动为进给运动。它主要用于加工高精度孔或一次定位完成多个孔的精加工，此外还可以从事与孔精加工有关的其他加工面的加工。使用不同的刀具和附件还可进行钻削、铣削、切削的加工精度和表面质量要高于钻床。",
			"钻床指主要用钻头在工件上加工孔的机床。通常钻头旋转为主运动，钻头轴向移动为进给运动。钻床结构简单，加工精度相对较低，可钻通孔、盲孔，更换特殊刀具，可扩、锪孔，铰孔或进行攻丝等加工。加工过程中工件不动，让刀具移动，将刀具中心对正孔中心，并使刀具转动（主运动）。钻床的特点是工件固定不动，刀具做旋转运动。",
			"磨床(grinder,grinding machine)是利用磨具对工件表面进行磨削加工的机床。 大多数的磨床是使用高速旋转的砂轮进行磨削加工，少数的是使用油石、砂带等其他磨具和游离磨料进行加工，如珩磨机、超精加工机床、砂带磨床、研磨机和抛光机等。",
			"机床（英文名称：machine tool）是指制造机器的机器，亦称工作母机或工具机，习惯上简称机床。一般分为金属切削机床、锻压机床和木工机床等。",
			"主轴箱是机床的重要的部件，是用于布置机床工作主轴及其传动零件和相应的附加机构的。主轴箱采用多级齿轮传动，通过一定的传动系统，经主轴箱内各个位置上的传动齿轮和传动轴，最后把运动传到主轴上，使主轴获得规定的转速和方向。",
			"卡盘是机床上用来夹紧工件的机械装置。利用均布在卡盘体上的活动卡爪的径向移动，把工件夹紧和定位的机床附件。卡盘一般由卡盘体、活动卡爪和卡爪驱动机构 3部分组成。卡盘体直径最小为65毫米，最大可达1500毫米，中央有通孔，以便通过工件或棒料；背部有圆柱形或短锥形结构，直接或通过法兰盘与机床主轴端部相联接。",
			"刀架是数控车床非常重要的部件。数控车床根据其功能，刀架上可安装的刀具数量—般为4把、6把、8把、10把12把、20把、24把，有些数控车床可以安装更多的刀具。",
			"溜板箱是将丝杆和光杆传来的旋转运动转变变成溜板箱的直线运动并带动刀架进给，控制刀架运动的接通，断开和换向。",
			"车床尾架在你加工长车件的时候可以使用后座顶针防止车件因长而夹不住要车加工吃到刀的横向力后掉下来,还有一个作用就是可以安装各种钻具,对车件进行钻孔或者打中心角之类的工作。",
			"轴(shaft)是穿在轴承中间或车轮中间或齿轮中间的圆柱形物件，但也有少部分是方型的。轴是支承转动零件并与之一起回转以传递运动、扭矩或弯矩的机械零件。一般为金属圆杆状，各段可以有不同的直径。",
			"手轮全称手动脉冲发生器，又称光电编码器。主要用于数控机床：立式加工中心、卧式加工中心、龙门加工中心等数控设备，如造型新颖，移动方便，抗干扰，带载能力强；全塑料外壳，绝缘强度高，防油污密封设计；具备X1，X10，X100三档倍率，可实现4轴倍率切换；具备控制开关、急停开关可选，人性设计，便于操作。",
			"齿轮箱在风力发电机组中的应用很广泛，在风力发电机组当中就经常用到，而且是一个重要的机械部件，齿轮箱其主要功用是将风轮在风力作用下所产生的动力传递给发电机并使其得到相应的转速。通常风轮的转速很低，远达不到发电机发电所要求的转速，必须通过齿轮箱齿轮副的增速作用来实现，故也将齿轮箱称之为增速箱。",
			"导轨( VAV guide):金属或其它材料制成的槽或脊，可承受、固定、引导移动装置或设备并减少其摩擦的一种装置。导轨表面上的纵向槽或脊，用于导引、固定机器部件、专用设备、仪器等。导轨又称滑轨、线性导轨、线性滑轨，用于直线往复运动场合，拥有比直线轴承更高的额定负载， 同时可以承担一定的扭矩，可在高负载的情况下实现高精度的直线运动。",
			"铸铁主要由铁、碳和硅组成的合金的总称。在这些合金中，含碳量超过在共晶温度时能保留在奥氏体固溶体中的量。",
			"灰铸铁是指具有片状石墨的铸铁，因断裂时断口呈暗灰色，故称为灰铸铁。要成分是铁、碳、硅、锰、硫、磷，是应用最广的铸铁，其产量占铸铁总产量80%以上。",
			"球墨铸铁是通过球化和孕育处理得到球状石墨，有效地提高了铸铁的机械性能，特别是提高了塑性和韧性，从而得到比碳钢还高的强度。",
			"棒料是针对于板材和其他型材而言，就是有一定长度的圆形或多边形长棍形状的材料。棒料一般不是铸件，一般是锻件的原材料，多通过拉伸而来，或者通过模具挤压出来，也可以进行机加工。",
			"工件（gōng jiàn），名词，多指在机械加工过程中的加工对象。也叫制件、作件、课件、五金件等。",
			"螺母就是螺帽,与螺栓或螺杆拧在一起用来起紧固作用的零件，所有生产制造机械必须用的一种元件根据材质的不同，分为碳钢、不锈钢、有色金属（如铜）等几大类型。",
			"螺杆，与螺栓或螺杆拧在一起用来起紧固作用的零件，所有生产制造机械必须用的一种元件根据材质的不同，分为碳钢、不锈钢、有色金属（如铜）等几大类型。",
			"麻花钻是通过其相对固定轴线的旋转切削以钻削工件的圆孔的工具。因其容屑槽成螺旋状而形似麻花而得名。",
			"镗杆是镗床，深孔钻镗床与相应的钻头、镗头、滚压头及组合镗滚头的联接杆。单节杆长有0.5米、1.2米、1.5米、1.7米、2米等，以适用不同机床、不同加工深度的需要。",
			"加工是通过一定工序和方式将原材料、半成品转化为目标需求的过程的总称。",
			"指令是命令旧时公文的一种，是上级对下级呈请的批示。另有解释，告诉计算机从事某一特殊运算的代码。如：数据传送指令、算术运算指令、位运算指令、程序流程控制指令、串操作指令、处理器控制指令。",
			"大型机，或者称大型主机，英文名mainframe。大型机使用专用的处理器指令集、操作系统和应用软件。大型机一词，最初是指装在非常大的带框铁盒子里的大型计算机系统，以用来同小一些的迷你机和微型机有所区别。",
			"现代人常说的应用，一般指手机和平板电脑的应用。在面向对象上通常分为个人用户应用（面向个人消费者）与企业级应用（面向企业），在移动端系统分类上主要包括iOSApp（如：同步推等）、Android Apk（如：AirDroid、百度应用等）和windows phone的xap和appx。",
			"处理器（CPU，Central Processing Unit）是一块超大规模的集成电路，是一台计算机的运算核心和控制核心。主要包括运算器（ALU，Arithmetic and Logic Unit）和控制器（CU，Control Unit）两大部件。",
			"暂时，近义词：临时、暂且、短暂，短时间内。",
			"长久的，长时间的",
			"通过已知量的可能的组合,获得新的量的数。",
			"指令系统是计算机硬件的语言系统，也叫机器语言，指机器所具有的全部指令的集合，它是软件和硬件的主要界面，反映了计算机所拥有的基本功能。",
			"方的形状",
			"金属是一种具有光泽（即对可见光强烈反射）、富有延展性、容易导电、导热等性质的物质。金属的上述特质都跟金属晶体内含有自由电子有关。在自然界中，绝大多数金属以化合态存在，少数金属例如金、铂、银、铋以游离态存在。金属矿物多数是氧化物及硫化物。",
			"在电磁学里，当两块磁铁或磁石相互吸引或排斥时，或当载流导线在周围产生磁场，促使磁针偏转指向，或当闭电路移动于不均匀磁场时，会有电流出现于闭电路，这些都是与磁有关的现象。",
			"屏蔽的原意是指遮蔽、阻挡的意思。",
			"机械加工中的加工对象。",
			"液压控制是以有压力液体作为控制信号传递方式的控制。用液压技术构成的控制系统称为液压控制系统。",
			"水箱一般有进水管、出水管（生活出水管、消防出水管）、溢流管、排水管，水箱按照功能不同分为生活水箱、消防水箱、生产水箱、人防水箱、家用水塔五种。",
			"过滤器（filter）是输送介质管道上不可缺少的一种装置，通常安装在减压阀、泄压阀、定水位阀 ,方工过滤器其它设备的进口端设备。",
			"海拔是指地面某个地点高出海平面的垂直距离。",
			"当物体与另一物体沿接触面的切线方向运动或有相对运动的趋势时，在两物体的接触面之间有阻碍它们相对运动的作用力，这种力叫摩擦力。",
			"交流电（alternating[ˈɔltərˌneɪt] current[ˈkɚrənt, ˈkʌr-]），简称为AC。交流电也称“交变电流”，简称“交流”。一般指大小和方向随时间作周期性变化的电压或电流。",
			"“直流电”（Direct Current，简称DC），又称“恒流电”，恒定电流是直流电的一种，是大小和方向都不变的直流电，1747年，美国的富兰克林根据实验提出电荷守恒定律，并且定义了正电和负电的术语。",
			"仪器，指科学技术上用于实验、计量、观测、检验、绘图等的器具或装置。通常是为某一特定用途所准备的一套装置或机器。",
			"鼓风机主要由下列六部分组成：电机、空气过滤器、鼓风机本体、空气室、底座（兼油箱）、滴油嘴。鼓风机靠汽缸内偏置的转子偏心运转，并使转子槽中的叶片之间的容积变化将空气吸入、压缩、吐出。在运转中利用鼓风机的压力差自动将润滑送到滴油嘴，滴入汽缸内以减少摩擦及噪声，同时可保持汽缸内气体不回流，此类鼓风机又称为滑片式鼓风机。",
			"控制器（英文名称：controller）是指按照预定顺序改变主电路或控制电路的接线和改变电路中电阻值来控制电动机的启动、调速、制动和反向的主令装置。",
			"起重机是指在一定范围内垂直提升和水平搬运重物的多动作起重机械。又称吊车。",
			"为发电机等（利用电磁感应原理工作的电气设备）提供工作磁场叫励磁。有时向发电机转子提供转子电源的装置也叫励磁。",
			"电动机（Motor）是把电能转换成机械能的一种设备。它是利用通电线圈（也就是定子绕组）产生旋转磁场并作用于转子（如鼠笼式闭合铝框）形成磁电动力旋转扭矩。",
			"整流器（英文：rectifier）是把交流电转换成直流电的装置，可用于供电装置及侦测无线电信号等。",
			"电阻器（Resistor）在日常生活中一般直接称为电阻。是一个限流元件，将电阻接在电路中后，电阻器的阻值是固定的一般是两个引脚，它可限制通过它所连支路的电流大小。",
			"电容器，通常简称其容纳电荷的本领为电容，用字母C表示。",
			"电感器(Inductor)是能够把电能转化为磁能而存储起来的元件。",
			"机械元件在转矩作用下都会产生一定程度的扭转变形，故转矩有时又称为扭矩",
			"卷扬机，用卷筒缠绕钢丝绳或链条提升或牵引重物的轻小型起重设备，又称绞车。",
			"比较，是指对比几种同类事物的异同、高下。",
			"物质每单位体积内的质量。",
			"柴油发动机是燃烧柴油来获取能量释放的发动机。",
			"范围，指界限，限制，一定的时空间限定",
			"车辆是“车”与车的单位“辆”的总称。",
			"简即简单，易即容易，综合为简易，也就是简单容易的缩写。",
			"可编程逻辑控制器，它采用一类可编程的存储器，用于其内部存储程序，执行逻辑运算、顺序控制、定时、计数与算术操作等面向用户的指令，并通过数字或模拟式输入/输出控制各种类型的机械或生产过程。",
			"人或事物所具有的特别或特殊之处。",
			"一段指向明确的代码",
			"便于携带的，体积小的",
			"机器人（Robot）是自动执行工作的机器装置。它既可以接受人类指挥，又可以运行预先编排的程序，也可以根据以人工智能技术制定的原则纲领行动。",
			"多种环境下都可使用的",
			"焊接，也称作熔接、镕接，是一种以加热、高温或者高压的方式接合金属或其他热塑性材料如塑料的制造工艺及技术。 ",
			"概念（Idea;Notion;Concept）人类在认识过程中，从感性认识上升到理性认识，把所感知的事物的共同本质特点抽象出来，加以概括，是本我认知意识的一种表达，就类形成概念式思维惯性。",
			"性能作为中药学术语应用时，泛指药物的四气、五味、归经、升降沉浮、补泻等特性和功能（在此，亦可作“效果”）。",
			"接口泛指实体把自己提供给外界的一种抽象化物（可以为另一实体），用以由内部操作分离出外部沟通方法，使其能被修改内部而不影响外界其他实体与其交互的方式。",
			"在C语言文件数据的输入输出中，当调用输入函数从外部文件中输入数据赋给程序中的变量时，这种操作成为“输入”或“读”。",
			"键盘是用于操作设备运行的一种指令和数据输入装置，也指经过系统安排操作一台机器或设备的一组功能键（如打字机、电脑键盘）。",
			"物理学上的压力，是指发生在两个物体的接触表面的作用力，或者是气体对于固体和液体表面的垂直作用力，或者是液体对于固体表面的垂直作用力。",
			"终端Terminal通常是指那些与集中式主机系统(例如IBM大型计算机)相连的“哑”用户设备。",
			"计算机辅助设计(CAD-Computer Aided Design)指利用计算机及其图形设备帮助设计人员进行设计工作。",
			"暂时不连接也可工作",
			"CAM (computer Aided Manufacturing，计算机辅助制造)的核心是计算机数值控制（简称数控），是将计算机应用于制造生产过程的过程或系统。"
	};
	private WordDB wordDB;
	private SQLiteDatabase dbWriter;
	private ImageView tab1btn,tab2btn,tab0btn;
	private TextView first2btn,welcombtn;
	private ViewPager vpager;
	private List<View> content;
	private LayoutInflater inflater;
	private MyWelcomeAdapter mdapter;
	private boolean isclick=false;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		new UITools(this,this).setStatusBar();
		SharedPreferences sharedPreferences= getSharedPreferences("test",Activity.MODE_PRIVATE); 
// 使用getString方法获得value，注意第2个参数是value的默认值 
		String name =sharedPreferences.getString("start_count","");
		if(name.equals("ab")){
			setContentView(R.layout.welcome);
			Start();	
		}else if(name.equals("")) {
			setContentView(R.layout.first_start);

			wordDB=new WordDB(this);
			dbWriter=wordDB.getWritableDatabase();
			createDB();
			vpager=(ViewPager)this.findViewById(R.id.first_start_viewpager);
			inflater=LayoutInflater.from(this);	
			content=new ArrayList<View>();
			//给viewpager加载页面
			View view0=inflater.inflate(R.layout.first0,null); 
			View view1=inflater.inflate(R.layout.first1,null);   
			View view2=inflater.inflate(R.layout.first2,null);
			content.add(view0);
			content.add(view1);
			content.add(view2);
			mdapter=new MyWelcomeAdapter();
			mdapter.notifyDataSetChanged();
			vpager.setAdapter(mdapter);
			startCount("ab");
			Toast.makeText(Welcome.this,"向左滑动进入!!",Toast.LENGTH_LONG).show();
			tab0btn=(ImageView)view0.findViewById(R.id.tab0btn1);
			tab1btn=(ImageView)view1.findViewById(R.id.tab1btn1);
			tab2btn=(ImageView)view2.findViewById(R.id.tab2btn1);
			first2btn=(TextView)view2.findViewById(R.id.first2Button1);
			tab0btn.setOnClickListener(new MyClick());
			tab1btn.setOnClickListener(new MyClick());
			tab2btn.setOnClickListener(new MyClick());
			first2btn.setOnClickListener(new MyClick());
			firstStart();
		}
    }
	public void firstStart(){
		SharedPreferences mySharedPreferences= getSharedPreferences("win",Activity.MODE_PRIVATE); 
//实例化SharedPreferences.Editor对象（第二步） 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
//用putString的方法保存数据 
		editor.putString("wincounter","0");
//提交当前数据 
		editor.commit(); 
	}
	public class MyClick implements OnClickListener
	{
		@Override
		public void onClick(View p1)
		{
			if(p1.getId()==R.id.tab0btn1||p1.getId()==R.id.tab1btn1||p1.getId()==R.id.tab2btn1||p1.getId()==R.id.first2Button1){
				Intent intent=new Intent();
				intent.setClass(Welcome.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		}
	}
	//保存数据
	public void startCount(String ab){
		SharedPreferences mySharedPreferences= getSharedPreferences("test",Activity.MODE_PRIVATE); 
//实例化SharedPreferences.Editor对象（第二步） 
		SharedPreferences.Editor editor = mySharedPreferences.edit(); 
//用putString的方法保存数据 
		editor.putString("start_count", ab);
//提交当前数据 
		editor.commit(); 
		
	}
	public  void createDB() {
		new Thread() {
			public void run() {
				for(int j=0;j<dc.length;j++){
					ContentValues cv=new ContentValues();
					cv.put(WordDB.ENWORD,dc[j].toString());
					cv.put(WordDB.CNWORD, dcN[j].toString());
					cv.put(WordDB.EXPLAIN, dcText[j].toString());
					cv.put(WordDB.TIME, getTime());
					dbWriter.insert(WordDB.TABLE_NAME, null, cv);
				}
			}
		}.start();
	}
	public String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date curDate = new Date();
		String str = format.format(curDate);
		return str;
	}
	public  void Start() {
		new Thread() {
			public void run() {
					
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent();
				intent.setClass(Welcome.this, MainActivity.class);
				startActivity(intent);
				finish();		
			}
		}.start();
    }

	public class MyWelcomeAdapter extends PagerAdapter
{
	//viewpager适配器
	@Override
	public int getCount()
	{
		return content.size();
	}
	@Override
	public boolean isViewFromObject(View p1, Object p2)
	{
		return p1==p2;
	}
	@Override
	public Object instantiateItem(View container, int position)
	{
		View view=content.get(position);
		((ViewPager)container).addView(view);
		return view;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		((ViewPager)container).removeView(content.get(position));
	}
}

}
