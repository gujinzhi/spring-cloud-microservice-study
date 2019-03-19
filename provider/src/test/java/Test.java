import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daqsoft.enctyption.Enctyption;

public class Test {

    public static void main(String[] s){
        String str  = "{\"ResponseStatus\":{\"Timestamp\":\"/Date(1552901705371+0800)/\",\"Ack\":\"Success\",\"Errors\":[],\"Extension\":[{\"Id\":\"CLOGGING_TRACE_ID\",\"Value\":\"3503923363578509103\"},{\"Id\":\"RootMessageId\",\"Value\":\"921812-0a0236d7-431361-30473\"}]},\"infoList\":[{\"date\":\"2019-03-14\",\"location\":\"西昌\",\"period\":\"daily\",\"value\":\"4400\"},{\"date\":\"2019-03-14\",\"location\":\"泸州\",\"period\":\"daily\",\"value\":\"4300\"}]}";
        JSONObject JSON = JSONObject.parseObject(str);
        JSONArray jsonArray =  JSON.getJSONArray("infoList");
        System.out.println(jsonArray.toJSONString());
    }
}
