class KarateChop1(object):
    @staticmethod
    def chop(element, array):
        arrayLen = len(array)
        mid = arrayLen // 2
        res = -1
        if arrayLen == 0:
            return res
        elif element < array[mid]:
            res = KarateChop1.chop(element, array[:mid])
        elif element > array[mid]:
            res = KarateChop1.chop(element, array[mid + 1:])
            if(res != -1):
                return res + mid + 1
        elif element == array[mid]:
            res = mid

        return res

