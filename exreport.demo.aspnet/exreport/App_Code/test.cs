using System;
using System.Collections.Generic;
using System.Text;
using org.qsoft.rpcfx.core;
using org.qsoft.rpcfx.bean;
using org.qsoft.rpcfx.util;
using org.qsoft.rpcfx.common;
using System.Web;
using System.Collections;

namespace nrpcfx.test
{
   


    public interface Itest
    {
        string doit(int i);
        //string doit(string s, int i);
        MyBean test();
    }

    public class Qtest : Itest
    {
        public string doit(int i)
        {
            return "doit[参数:" + i.ToString() + "]";
        }

        public MyBean test()
        {
            MyBean bean = new MyBean();
            bean.id = 1;
            bean.ids = new int[] {11,12,13,14};
            bean.name = "第一个bean";
            return bean;
        }
    }

    public class MyBean
    {
        private int _id;

        public int id
        {
            get { return _id; }
            set { _id = value; }
        }


        private string _name;

        public string name
        {
            get { return _name; }
            set { _name = value; }
        }


        private int[] _ids;

        public int[] ids
        {
            get { return _ids; }
            set { _ids = value; }
        }


        private MyBean _mb;

        public MyBean mb
        {
            get { return _mb; }
            set { _mb = value; }
        }

    }
}


