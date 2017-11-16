<<<<<<< HEAD
﻿namespace org.tec.datastructures.Tests
{
    public class QueueTest
    {
        
    }
=======
﻿using NUnit.Framework;
using org.tec.datastructures.Linear;

namespace org.tec.datastructures.Tests{
    
    [TestFixture()]
    public class QueueTest{
        
        [Test]
        public void testEnqueue() {
            Queue<int> tester = new Queue<int>();
            Assert.Equals(tester.Size(), 0);
            Assert.Null(tester.Peak());
            tester.Enqueue(0);
            Assert.NotNull(tester.Peak());
            tester.Enqueue(1);
            tester.Enqueue(2);
            Assert.Equals(tester.Size(), 3);
        }
    
        [Test]
        public void testDequeue() {
            Queue<int> tester = new Queue<int>();
            tester.Enqueue(0);
            tester.Enqueue(1);
            tester.Enqueue(2);
            Assert.Equals(tester.Dequeue().GetValue(), 0);
            Assert.Equals(tester.Dequeue().GetValue(), 1);
            Assert.Equals(tester.Dequeue().GetValue(), 2);
            Assert.Null(tester.Dequeue());
            Assert.Equals(tester.Size(), 0);
        }
    
        [Test]
        public void TestPeak() {
            Queue<int> tester = new Queue<int>();
            tester.Enqueue(0);
            tester.Enqueue(1);
            tester.Enqueue(2);
            Assert.Equals(tester.Size(), 3);
            Assert.Equals(tester.Peak().GetValue(), 0);
            Assert.Equals(tester.Size(), 3);
            tester.Dequeue();
            Assert.Equals(tester.Size(), 2);
            Assert.Equals(tester.Peak().GetValue(), 1);
            Assert.Equals(tester.Size(), 2);
            tester.Clear();
            Assert.Null(tester.Peak());
        }
        
    }
>>>>>>> branch 'master' of https://github.com/Greibus/Dashboard.git
}