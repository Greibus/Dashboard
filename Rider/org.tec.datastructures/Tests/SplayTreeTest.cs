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
        Assert.AreEqual(20, tree.GetRoot().Value);

        tree.Append(30);
        Assert.AreEqual(30, tree.GetRoot().Value);
        Assert.AreEqual(20, tree.GetRoot().Left.Value);

        tree.Append(40);
        Assert.AreEqual(40, tree.GetRoot().Value);

        tree.Append(0);
        Assert.AreEqual(0, tree.GetRoot().Value);
        Assert.AreEqual(40, tree.GetRoot().Right.Value);
    }

    
  }
}
