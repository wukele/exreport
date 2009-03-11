using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.Text;
using System.Collections;
using nrpcfx.org.qsoft.rpcfx.bean.json;
using com.qsoft.commons.beanutils;
namespace exreport.demo
{
    /// <summary>
    /// JsonReport 的摘要说明
    /// </summary>
    public class JsonReport
    {
        public  const string JAF_SIGNAL = ",";

        public  const string EXCEL_DATA_LENGTH = "EXCEL_DATA_LENGTH";

        public  const string HIDDEN_FIELDS = "HIDDEN_FIELDS";

        public  const string REPORT_DTO_FIELDS_KEY = "REPORT_DTO_FIELDS_KEY";

        public  const string DTO_DATA_ARRAYS = "DTO_DATA_ARRAYS";

        public  const string META_DATA_ARRAYS = "META_DATA_ARRAYS";

        /**
         * @param map
         * @return json 
         */
        public static string createExreportJson(Hashtable map)
        {

            ICollection set = map.Keys;

            ArrayList list = new ArrayList();

            
            IEnumerator ie = set.GetEnumerator();
            while (ie.MoveNext())
            {
                object obj = ie.Current;
                if (!obj.ToString().EndsWith(DTO_DATA_ARRAYS)
                        && !obj.ToString().EndsWith(META_DATA_ARRAYS))
                {
                    list.Add(obj.ToString());
                }
            }

            map.Add(META_DATA_ARRAYS, list.ToArray());

            QJsonWriter writer = new QJsonWriter();
            writer.write(map, null);

            return writer.result();
        }

        public static string[] convert(object[] arrays, string[] properties)
        {

            if (arrays == null || arrays.Length == 0)
                return null;

            string[] array = new string[properties.Length];
            StringBuilder[] arraysb = new StringBuilder[properties.Length];

            string value = null;
            for (int i = 0; i < arrays.Length; i++)
            {
                object dto = arrays[i];
                for (int j = 0; j < properties.Length; j++)
                {
                    try
                    {
                        object o = BeanUtils.getProperty(dto, properties[j]);
                        value = (o == null) ? "" : o.ToString();

                        if (value == null)
                        {
                            value = "";
                        }

                        value = value.Replace(',', ' ');

                        if (i == 0)
                        {
                            arraysb[j] = new StringBuilder();
                            arraysb[j].Append(value);
                        }
                        else
                        {
                            arraysb[j].Append(JAF_SIGNAL);
                            arraysb[j].Append(value);
                        }

                    }
                    catch (Exception e)
                    {

                    }
                }
            }

            for (int j = 0; j < properties.Length; j++)
            {
                array[j] = arraysb[j].ToString().Replace("\r\n", "");
            }

            return array;

        }

        //public static string[] convert(List dtoList, string[] properties)
        //{

        //    if (dtoList == null || dtoList.size() == 0)
        //        return null;

        //    if (properties == null)
        //    {
        //        try
        //        {
        //            string[] beanpros = (string[])BeanUtils.describe(
        //                    dtoList.get(0)).keySet().toArray(new string[] { });

        //            if (beanpros != null) return convert(dtoList.toArray(), beanpros);

        //        }
        //        catch (IllegalAccessException e)
        //        {
        //            e.printStackTrace();
        //        }
        //        catch (InvocationTargetException e)
        //        {
        //            e.printStackTrace();
        //        }
        //        catch (NoSuchMethodException e)
        //        {
        //            e.printStackTrace();
        //        }
        //    }

        //    return convert(dtoList.toArray(), properties);

        //}
    }
}
