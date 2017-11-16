<<<<<<< HEAD
﻿namespace org.tec.datastructures.Tests
{
    public class CircularListTest
    {
        
    }
=======
﻿using System;
using NUnit.Framework;
using org.tec.datastructures.Linear;

namespace org.tec.datastructures.Tests
{
    
    [TestFixture]
    public class CircularListTest{
        
        [Test]
        public void AppendTest()
        {
            CircularList<string> list = new CircularList<string>();
            list.Append("Prueba");
            list.Append("Prueba2");
            Assert.AreEqual("Prueba", list.Search("Prueba"));
            Assert.AreEqual("Prueba2", list.Search("Prueba2"));
        }

        [Test]
        public void TestDeleteHead() {
            CircularList<int> tester = new CircularList<int>();
            tester.Append(0);
            tester.Delete(0);
            tester.Append(1);
            tester.Append(2);
            Assert.Null(tester.Search(0));
            Assert.NotNull(tester.GetHead());
            Object newHead = tester.GetHead().GetValue();
            Assert.AreEqual(newHead, 1);
            Assert.AreEqual(tester.Length(), 2);
        }
    
        [Test]
        public void TestDeleteLast() {
            CircularList<int> tester = new CircularList<int>();      
            tester.Append(0);
            tester.Append(1);
            tester.Append(2);
            tester.Delete(2);
            Assert.AreEqual(tester.Length(), 2);
        }

        [Test]
        public void TestDeleteMiddle() {
            CircularList<int> tester = new CircularList<int>();
            tester.Append(0);
            tester.Append(1);
            tester.Append(2);
            tester.Delete(1);
            Assert.AreEqual(tester.GetHead().GetNext().GetValue(), 2);
            Assert.AreEqual(tester.Length(), 2);
        }

        [Test]
        public void IsEmptyTest(){
            CircularList<int> list = new CircularList<int>();
            Assert.IsTrue(list.IsEmpty());
            list.Append(4);
            Assert.IsFalse(list.IsEmpty());
        }

        [Test]
        public void LengthTest()
        {
            CircularList<int> list = new CircularList<int>();
            list.Append(4);
            list.Append(9);
            Assert.AreEqual(2, list.Length());
        }
        
        
    }
>>>>>>> branch 'master' of https://github.com/Greibus/Dashboard.git
}