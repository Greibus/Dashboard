using NUnit.Framework;
using org.tec.datastructures.Hierarchical;

namespace org.tec.datastructures.Tests{
    
    [TestFixture]
    public class BinaryTreeTest{
    
        [Test]
        public void AppendTest()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            Assert.DoesNotThrow(delegate {
                tree.Append(3);
            });
            Assert.DoesNotThrow(delegate {
                tree.Append(5);
            });
            Assert.DoesNotThrow(delegate {
                tree.Append(28);
            });
        }

        [Test]
        public void SearchTest()
        {
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Append(3);
            Assert.IsNotNull(tree.Search(3));

            Assert.DoesNotThrow(delegate {
                tree.Append(5);
                tree.Search(3);
                tree.Search(5);
            });
            Assert.DoesNotThrow(delegate {
                tree.Append(28);
                tree.Search(3);
                tree.Search(5);
                tree.Search(14);
                tree.Search(28);
            });
            Assert.AreEqual(5, tree.Search(5).GetValue());
            Assert.AreEqual(3, tree.Search(3).GetValue());
            Assert.AreEqual(28, tree.Search(28).GetValue());
        }

        [Test]
        public void DeleteTest(){
            BinaryTree<int> tree = new BinaryTree<int>();
            tree.Append(5);
            tree.Append(3);
            tree.Append(9);
            tree.Append(14);
            tree.Append(1);
            tree.Append(23);
            tree.Delete(14);
            Assert.IsNull(tree.Search(14));
            tree.Delete(3);
            Assert.IsNull(tree.Search(3));
        }
    }
                      
}
