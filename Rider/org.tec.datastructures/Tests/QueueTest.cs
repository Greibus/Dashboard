
 using NUnit.Framework;
using org.tec.datastructures.Linear;

namespace org.tec.datastructures.Tests{
    
    [TestFixture()]
    public class QueueTest{
        
        [Test]
        public void testEnqueue() {
            Queue<int> tester = new Queue<int>();
            Assert.AreEqual(tester.Size(), 0);
            Assert.Null(tester.Peak());
            tester.Enqueue(0);
            Assert.NotNull(tester.Peak());
            tester.Enqueue(1);
            tester.Enqueue(2);
            Assert.AreEqual(tester.Size(), 3);
        }
    
        [Test]
        public void testDequeue() {
            Queue<int> tester = new Queue<int>();
            tester.Enqueue(0);
            tester.Enqueue(1);
            tester.Enqueue(2);
            Assert.AreEqual(tester.Dequeue().GetValue(), 0);
            Assert.AreEqual(tester.Dequeue().GetValue(), 1);
            Assert.AreEqual(tester.Dequeue().GetValue(), 2);
            Assert.Null(tester.Dequeue());
            Assert.AreEqual(tester.Size(), 0);
        }
    
        [Test]
        public void TestPeak() {
            Queue<int> tester = new Queue<int>();
            tester.Enqueue(0);
            tester.Enqueue(1);
            tester.Enqueue(2);
            Assert.AreEqual(tester.Size(), 3);
            Assert.AreEqual(tester.Peak().GetValue(), 0);
            Assert.AreEqual(tester.Size(), 3);
            tester.Dequeue();
            Assert.AreEqual(tester.Size(), 2);
            Assert.AreEqual(tester.Peak().GetValue(), 1);
            Assert.AreEqual(tester.Size(), 2);
            tester.Clear();
            Assert.Null(tester.Peak());
        }
        
    }
}