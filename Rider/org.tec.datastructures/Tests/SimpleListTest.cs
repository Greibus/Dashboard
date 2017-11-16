
﻿using System;
using NUnit.Framework;
using org.tec.datastructures.Linear;

namespace org.tec.datastructures.Tests{
    
    [TestFixture]
    public class SimpleListTest{
        
    [Test]
    public void testAppendFirst() {
        SimpleList<int> tester = new SimpleList<int>();
        Assert.Null(tester.GetHead());
        tester.Append(0);
        Assert.NotNull(tester.GetHead());
        Object appended = tester.GetHead().GetValue();
        Assert.AreEqual(appended, 0);
    }
    
    [Test]
    public void testAppend() {
        SimpleList<int> tester = new SimpleList<int>();
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        Object appended = tester.GetHead().GetNext().GetNext().GetValue();
        Assert.AreEqual(appended, 2);
    }
    
    [Test]
    public void testLength() {
        SimpleList<int> tester = new SimpleList<int>();
        Assert.AreEqual(tester.Length(), 0);
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        Assert.AreEqual(tester.Length(), 3);
    }
    
    [Test]
    public void testSearch() {
        SimpleList<int> tester = new SimpleList<int>();
        Assert.Null(tester.Search(0));
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        Object searchedFirst = tester.Search(0).GetValue();
        Assert.AreEqual(searchedFirst, 0);
        Object searchedMiddle = tester.Search(1).GetValue();
        Assert.AreEqual(searchedMiddle, 1);
        Object searchedLast = tester.Search(2).GetValue();
        Assert.AreEqual(searchedLast, 2);
    }
    
    [Test]
    public void testDeleteHead() {
        SimpleList<int> tester = new SimpleList<int>();
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        tester.Delete(0);
        Assert.Null(tester.Search(0));
        Assert.NotNull(tester.GetHead());
        Object newHead = tester.GetHead().GetValue();
        Assert.AreEqual(newHead, 1);
        Assert.AreEqual(tester.Length(), 2);
    }
    
    [Test]
    public void testDeleteLast() {
        SimpleList<int> tester = new SimpleList<int>();
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        tester.Delete(2);
        Assert.Null(tester.GetHead().GetNext().GetNext());
        Assert.AreEqual(tester.Length(), 2);
    }
    
    [Test]
    public void testDeleteMiddle() {
        SimpleList<int> tester = new SimpleList<int>();
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        tester.Delete(1);
        Assert.AreEqual(tester.GetHead().GetNext().GetValue(), 2);
        Assert.AreEqual(tester.Length(), 2);
    }
    
    [Test]
    public void testEmpty() {
        SimpleList<int> tester = new SimpleList<int>();
        Assert.True(tester.IsEmpty());
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        Assert.False(tester.IsEmpty());
        tester.Clear();
        Assert.True(tester.IsEmpty());
    }
        
    }
}