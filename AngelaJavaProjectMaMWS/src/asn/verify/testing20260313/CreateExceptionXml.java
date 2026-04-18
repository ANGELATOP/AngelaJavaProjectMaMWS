package asn.verify.testing20260313;

import java.util.ArrayList;
import java.util.List;

public class CreateExceptionXml {

	public static void main(String[] args) {
		buildErrorXml();

	}

	public static void buildErrorXml() {
		List<String> list = new ArrayList<String>();
		List<String> loop = new ArrayList<String>();

		list.add("<Error>");

		loop = loop();
		for (int i = 0; i < 10; i++) {
			for (String l : loop) {
				list.add(l);
			}
		}

		for (int i = 0; i < 10; i++) {
			list.add("</RecoverableException>");
		}
		list.add("</Error>");

		for (String err : list) {
			System.out.println(err);
		}

	}

	public static List<String> loop() {
		List<String> list = new ArrayList<String>();
		List<String> loop = new ArrayList<String>();

		loop = buildLineDetails();
		for (String l : loop) {
			list.add(l);
		}
//		list.add("</RecoverableException>");

		return list;
	}

	public static List<String> buildLineDetails() {
		List<String> list = new ArrayList<String>();

		list.add("<RecoverableException>");
		list.add(
				"     <File>/build/jenkins_swg/slot0/product-build/WMB/src/DataFlowEngine/PluginInterface/jlinklib/ImbJniNode.cpp");
		list.add("     </File>");
		list.add("     <Line>423</Line>");
		list.add("     <Function>ImbJniNode::evaluate</Function>");
		list.add("     <Type>ComIbmMSLMappingNode</Type>");
		list.add("     <Name>TestPullTrigger#FCMComposite_1_1.MaWMS_CommonLIB.PullMessageTester#FCMComposite_1_25");
		list.add("     </Name>");
		list.add("     <Label>TestPullTrigger.PullMessageTester.buildTokenRequest</Label>");
		list.add("     <Catalog>BIPmsgs</Catalog>");
		list.add("     <Severity>3</Severity>");
		list.add("     <Number>2230</Number>");
		list.add("     <Text>Caught exception and rethrowing</Text>");
		list.add("     <Insert>");
		list.add("     	<Type>14</Type>");
		list.add("     	<Text>TestPullTrigger.PullMessageTester.buildTokenRequest</Text>");
		list.add("     </Insert>");

		return list;
	}
}
