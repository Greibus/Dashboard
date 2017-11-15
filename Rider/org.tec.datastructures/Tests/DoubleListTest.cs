using System;
using NUnit.Framework;
using org.tec.datastructures.Linear;

namespace org.tec.datastructures.Tests{
    
    [TestFixture]
    public class DoubleListTest{
      
    [Test]
    public void testAppendFirst() {
        DoubleList<int> tester = new DoubleList<int>();
        Assert.Null(tester.GetHead());
        tester.Append(0);
        Assert.NotNull(tester.GetHead());
        Object appended = tester.GetHead().GetValue();
        Assert.Equals(appended, 0);
    }
    
    [Test]
    public void testAppend() {
        DoubleList<int> tester = new DoubleList<int>();
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        Object appended = tester.GetHead().GetNext().GetNext().GetValue();
        Assert.Equals(appended, 2);
        Assert.Equals(tester.GetHead().GetNext().GetPrevious(), tester.GetHead());
    }
    
    [Test]
    public void testLength() {
        DoubleList<int> tester = new DoubleList<int>();
        Assert.Equals(tester.Length(), 0);
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        Assert.Equals(tester.Length(), 3);
    }
    
    [Test]
    public void testSearch() {
        DoubleList<int> tester = new DoubleList<int>();
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        Object searchedFirst = tester.Search(0).GetValue();
        Assert.Equals(searchedFirst, 0);
        Object searchedMiddle = tester.Search(1).GetValue();
        Assert.Equals(searchedMiddle, 1);
        Object searchedLast = tester.Search(2).GetValue();
        Assert.Equals(searchedLast, 2);
    }
    
    [Test]
    public void testDeleteHead() {
        DoubleList<int> tester = new DoubleList<int>();
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        tester.Delete(0);
        Assert.Null(tester.Search(0));
        Assert.NotNull(tester.GetHead());
        Object newHead = tester.GetHead().GetValue();
        Assert.Equals(newHead, 1);
        Assert.Equals(tester.Length(), 2);
    }
    
    [Test]
    public void testDeleteLast() {
        DoubleList<int> tester = new DoubleList<int>();
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        tester.Delete(2);
        Assert.Null(tester.GetHead().GetNext().GetNext());
        Assert.Equals(tester.Length(), 2);
    }
    
    [Test]
    public void testDeleteMiddle() {
        DoubleList<int> tester = new DoubleList<int>();
        tester.Append(0);
        tester.Append(1);
        tester.Append(2);
        tester.Delete(1);
        Object value = tester.GetHead().GetNext().GetValue();
        Assert.Equals(value, 2);
        Assert.Equals(tester.Length(), 2);
    }
    
    [Test]
    public void testEmpty() {
        DoubleList<int> tester = new DoubleList<int>();
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