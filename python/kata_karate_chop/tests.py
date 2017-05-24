import unittest
from karate_chop2 import *

class KarateChopTests(unittest.TestCase):
    def test(self):
        self.assertEqual(-1, KarateChop2.chop(3, []))
        self.assertEqual(-1, KarateChop2.chop(3, [1]))
        self.assertEqual(0, KarateChop2.chop(1, [1]))

        self.assertEqual(0, KarateChop2.chop(1, [1, 3, 5]))
        self.assertEqual(1, KarateChop2.chop(3, [1, 3, 5]))
        self.assertEqual(2, KarateChop2.chop(5, [1, 3, 5]))
        self.assertEqual(-1, KarateChop2.chop(0, [1, 3, 5]))
        self.assertEqual(-1, KarateChop2.chop(2, [1, 3, 5]))
        self.assertEqual(-1, KarateChop2.chop(4, [1, 3, 5]))
        self.assertEqual(-1, KarateChop2.chop(6, [1, 3, 5]))

        self.assertEqual(0, KarateChop2.chop(1, [1, 3, 5, 7]))
        self.assertEqual(1, KarateChop2.chop(3, [1, 3, 5, 7]))
        self.assertEqual(2, KarateChop2.chop(5, [1, 3, 5, 7]))
        self.assertEqual(3, KarateChop2.chop(7, [1, 3, 5, 7]))
        self.assertEqual(-1, KarateChop2.chop(0, [1, 3, 5, 7]))
        self.assertEqual(-1, KarateChop2.chop(2, [1, 3, 5, 7]))
        self.assertEqual(-1, KarateChop2.chop(4, [1, 3, 5, 7]))
        self.assertEqual(-1, KarateChop2.chop(6, [1, 3, 5, 7]))
        self.assertEqual(-1, KarateChop2.chop(8, [1, 3, 5, 7]))

if __name__ == '__main__':
    unittest.main()
