
 using NUnit.Framework;
 using org.tec.datastructures.Linear;
 using org.tec.datastructures.Nodes;

namespace org.tec.datastructures.Tests{
    
    [TestFixture]
    public class StackTest{
        
            
        [Test]
        public void testPush() {
            Stack<int> tester = new Stack<int>();
            Assert.Null(tester.Peak());
            tester.Push(0);
            Assert.NotNull(tester.Peak());
            Assert.AreEqual(tester.Peak().GetValue(), 0);
            tester.Push(1);
            Assert.AreEqual(tester.Peak().GetValue(), 1);
            tester.Push(2);
            Assert.AreEqual(tester.Peak().GetValue(), 2);
            Assert.AreEqual(tester.Size(), 3);
        }
    
        [Test]
        public void testPop() {
            Stack<int> tester = new Stack<int>();
            tester.Push(0);
            tester.Push(1);
            tester.Push(2);
            SimpleNode<int> popped = tester.Pop();
            Assert.AreEqual(popped.GetValue(), 2);
            popped = tester.Pop();
            Assert.AreEqual(popped.GetValue(), 1);
            Assert.AreEqual(tester.Size(), 1);
            popped = tester.Pop();
            Assert.AreEqual(popped.GetValue(), 0);
            Assert.Null(tester.Pop());
        }
    
        [Test]
        public void testPeak() {
            Stack<int> tester = new Stack<int>();
            Assert.Null(tester.Peak());
            tester.Push(0);
            Assert.AreEqual(tester.Peak().GetValue(), 0);
            tester.Push(1);
            Assert.AreEqual(tester.Peak().GetValue(), 1);
            tester.Push(2);
            Assert.AreEqual(tester.Peak().GetValue(), 2);
            tester.Clear();
            Assert.Null(tester.Peak());
            Assert.AreEqual(tester.Size(), 0);
        }
        
        
    }
}