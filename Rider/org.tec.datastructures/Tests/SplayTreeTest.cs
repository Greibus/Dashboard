using NUnit.Framework;
using org.tec.datastructures.Hierarchical;

namespace org.tec.datastructures.Tests {
    
    [TestFixture()]
    public class SplayTreeTest{
    
    [Test()]
    public void AppendTest()
    {
        SplayTree<int> tree = new SplayTree<int>();
        tree.Append(20);
        Assert.AreEqual(20, tree.getRoot().value);

        tree.Append(30);
        Assert.AreEqual(30, tree.getRoot().value);
        Assert.AreEqual(20, tree.getRoot().left.value);

        tree.Append(40);
        Assert.AreEqual(40, tree.getRoot().value);

        tree.Append(0);
        Assert.AreEqual(0, tree.getRoot().value);
        Assert.AreEqual(40, tree.getRoot().right.value);
    }

    
  }
}
