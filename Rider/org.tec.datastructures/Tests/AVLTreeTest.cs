<<<<<<< HEAD
﻿namespace org.tec.datastructures.Tests
{
    public class AVLTreeTest
    {
        
    }
=======
﻿using NUnit.Framework;
using org.tec.datastructures.Hierarchical;

namespace org.tec.datastructures.Tests{
    
    
    [TestFixture]
    public class AvlTreeTest{
      
        [Test]
        public void AppendTest()
        {
            AvlTree<int> tree = new AvlTree<int>();
            tree.Append(20);
            Assert.AreEqual(20, tree.GetRoot().GetValue());

            tree.Append(30);
            Assert.AreEqual(20, tree.GetRoot().GetValue());
            Assert.AreEqual(30, tree.GetRoot().GetRight().GetValue());

            tree.Append(40);
            Assert.AreEqual(30, tree.GetRoot().GetValue());

            tree.Append(0);
            tree.Append(10);
            Assert.AreEqual(10, tree.GetRoot().GetLeft().GetValue());
            Assert.AreEqual(0, tree.GetRoot().GetLeft().GetLeft().GetValue());
            Assert.AreEqual(20, tree.GetRoot().GetLeft().GetRight().GetValue());
        }

        [Test]
        public void SearchTest()
        {
            AvlTree<int> tree = new AvlTree<int>();
            tree.Append(20);
            tree.Append(30);
            tree.Append(40);
            tree.Append(0);
            tree.Append(10);
            Assert.AreEqual(10, tree.Search(10).GetValue());
            Assert.AreEqual(0, tree.Search(0).GetValue());
            Assert.AreEqual(40, tree.Search(40).GetValue());
        }

        [Test]
        public void DeleteTest()
        {
            AvlTree<int> tree = new AvlTree<int>();
            tree.Append(20);
            tree.Append(30);
            tree.Append(40);
            tree.Append(0);
            tree.Append(10);
            tree.Delete(40);
            Assert.AreEqual(10, tree.GetRoot().GetValue());
            Assert.AreEqual(30, tree.GetRoot().GetRight().GetValue());
            Assert.DoesNotThrow(delegate
            {
                tree.Delete(40);
            });
            tree.Delete(0);
            Assert.AreEqual(20, tree.GetRoot().GetValue());
            Assert.AreEqual(30, tree.GetRoot().GetRight().GetValue());
            Assert.AreEqual(10, tree.GetRoot().GetLeft().GetValue());
        }
        
        
    }
>>>>>>> branch 'master' of https://github.com/Greibus/Dashboard.git
}