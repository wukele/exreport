using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.Collections;

namespace exreport.demo
{


    /// <summary>
    /// TestReport 的摘要说明
    /// </summary>
    public class TestReport
    {
        public static string test(long len)
        {
            Hashtable m = new Hashtable();

            RDto[] objs = report(len);
            string[] strs = JsonReport.convert(objs, new string[] { "code", "name", "area", "sum", "lastmonth", "lastyear", "term" });
            m.Add(JsonReport.DTO_DATA_ARRAYS, strs);

            m.Add("number", 111111111L);
            m.Add("date", new DateTime().ToShortDateString());
            m.Add("unit", "万元");
            m.Add("industry", "金融");
            m.Add("area", "beijing");
            m.Add("owner", "国有");
            m.Add(JsonReport.REPORT_DTO_FIELDS_KEY, "code,name,area,sum,lastmonth,lastyear,term");
            m.Add(JsonReport.HIDDEN_FIELDS, "lastmonth,lastyear");
            m.Add(JsonReport.EXCEL_DATA_LENGTH, objs.Length);
            string ss = JsonReport.createExreportJson(m);
            return ss;
        }

        private static RDto[] report(long len)
        {
            Random r = new Random();

            string[] areas = { "东城", "海淀", "朝阳" };
            RDto[] rd = new RDto[len];
            for (int i = 0; i < rd.Length; i++)
            {
                rd[i] = new RDto();
                rd[i].code = (string.Format("{0:D2}",i % 100));
                rd[i].name = ("北京." + (int)(r.NextDouble() * 10));
                rd[i].area = areas[(int)(r.NextDouble() * 3)];
                rd[i].sum = (long)(r.NextDouble() * 10000);
                rd[i].lastmonth = (long)(r.NextDouble() * 10000);
                rd[i].lastyear = (long)(r.NextDouble() * 10000);
                rd[i].term = (long)(r.NextDouble() * 10000);
            }
            return rd;
        }
    }

    public class RDto
    {

        private string _code;
        private string _name;
        private string _area;
        private long _sum;
        private long _lastmonth;
        private long _lastyear;
        private long _term;

        public string code
        {
            set { this._code = value; }
            get { return this._code; }
        }
        public string name
        {
            set { this._name = value; }
            get { return this._name; }
        }
        public string area
        {
            set { this._area = value; }
            get { return this._area; }
        }
        public long sum
        {
            set { this._sum = value; }
            get { return this._sum; }
        }
        public long lastmonth
        {
            set { this._lastmonth = value; }
            get { return this._lastmonth; }
        }
        public long lastyear
        {
            set { this._lastyear = value; }
            get { return this._lastyear; }
        }
        public long term
        {
            set { this._term = value; }
            get { return this._term; }
        }

    }
}