import unittest
from karate_chop1 import *

class KarateChopTests(unittest.TestCase):
    def test(self):
        self.assertEqual(-1, KarateChop1.chop(3, []))
        self.assertEqual(-1, KarateChop1.chop(3, [1]))
        self.assertEqual(0, KarateChop1.chop(1, [1]))

        self.assertEqual(0, KarateChop1.chop(1, [1, 3, 5]))
        self.assertEqual(1, KarateChop1.chop(3, [1, 3, 5]))
        self.assertEqual(2, KarateChop1.chop(5, [1, 3, 5]))
        self.assertEqual(-1, KarateChop1.chop(0, [1, 3, 5]))
        self.assertEqual(-1, KarateChop1.chop(2, [1, 3, 5]))
        self.assertEqual(-1, KarateChop1.chop(4, [1, 3, 5]))
        self.assertEqual(-1, KarateChop1.chop(6, [1, 3, 5]))

        self.assertEqual(0, KarateChop1.chop(1, [1, 3, 5, 7]))
        self.assertEqual(1, KarateChop1.chop(3, [1, 3, 5, 7]))
        self.assertEqual(2, KarateChop1.chop(5, [1, 3, 5, 7]))
        self.assertEqual(3, KarateChop1.chop(7, [1, 3, 5, 7]))
        self.assertEqual(-1, KarateChop1.chop(0, [1, 3, 5, 7]))
        self.assertEqual(-1, KarateChop1.chop(2, [1, 3, 5, 7]))
        self.assertEqual(-1, KarateChop1.chop(4, [1, 3, 5, 7]))
        self.assertEqual(-1, KarateChop1.chop(6, [1, 3, 5, 7]))
        self.assertEqual(-1, KarateChop1.chop(8, [1, 3, 5, 7]))

if __name__ == '__main__':
    unittest.main()
