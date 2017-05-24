class KarateChop2(object):
    @staticmethod
    def chop(element, array):
        first = 0
        last = len(array) - 1
        mid = last // 2
        while first <= last:
            if element == array[mid]:
                return mid
            elif element < array[mid]:
                last = mid - 1
            elif element > array[mid]:
                    first = mid + 1
            mid = (last + first) // 2
        return -1

